package com.example.nf.newfine_backend.student.service;

import com.example.nf.newfine_backend.student.domain.Student;
import com.example.nf.newfine_backend.student.dto.*;
import com.example.nf.newfine_backend.student.dto.response.SingleResult;
import com.example.nf.newfine_backend.student.exception.CustomException;
import com.example.nf.newfine_backend.student.jwt.RefreshToken;
import com.example.nf.newfine_backend.student.jwt.TokenProvider;
import com.example.nf.newfine_backend.student.repository.StudentRepository;
import com.example.nf.newfine_backend.student.repository.RefreshTokenRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import java.util.concurrent.TimeUnit;

import static com.example.nf.newfine_backend.student.exception.ErrorCode.*;

@Service
@RequiredArgsConstructor
//@AllArgsConstructor
public class AuthService {

    private final AuthenticationManagerBuilder authenticationManagerBuilder;
    private final StudentRepository studentRepository;
    private final PasswordEncoder passwordEncoder;
    private final TokenProvider tokenProvider;
    private final RefreshTokenRepository refreshTokenRepository;
    private final PointService pointService;
    private final RedisTemplate redisTemplate;
    private final ResponseService responseService;

//    @Value("${jwt.secret}")
//    private final String secretKey;
//
//    private Key secretK;

//    public void initK() {
//        byte[] keyBytes = Decoders.BASE64.decode(secretKey);
//        this.secretK = Keys.hmacShaKeyFor(keyBytes);
//    }

    @Transactional
    public StudentResponseDto signup(SignUpDto signUpDto) {
        if (studentRepository.existsByPhoneNumber(signUpDto.getPhoneNumber())) {
            throw new CustomException(DUPLICATE_MEMBER);
        }

        Student student = signUpDto.toMember(passwordEncoder);
        student.setPoint(0);
        return StudentResponseDto.of(studentRepository.save(student));
    }

    // 로그인 예외처리**
    @Transactional
    public TokenDto login(SignInDto signInDto) {
        // 1. Login ID/PW 를 기반으로 AuthenticationToken 생성 (인증 정보 객체 UsernamePasswordAuthenticationToken 생성)
        UsernamePasswordAuthenticationToken authenticationToken = signInDto.toAuthentication();

        // 2. 실제로 검증 (사용자 비밀번호 체크) 이 이루어지는 부분
        //    authenticate 메서드가 실행이 될 때 CustomUserDetailsService 에서 만들었던 loadUserByUsername 메서드가 실행됨
        //    인증 완료된 authentication 에는 Member ID 저장됨
        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);

        // 3. 인증 정보를 기반으로 JWT 토큰 생성 (Access Token + Refresh Token)
        TokenDto tokenDto = tokenProvider.generateTokenDto(authentication);

        // 4. RefreshToken 저장
//        RefreshToken refreshToken = RefreshToken.builder()
//                .key(authentication.getName())
//                .value(tokenDto.getRefreshToken())
//                .build();
//
//        refreshTokenRepository.save(refreshToken);

        // 4. RefreshToken Redis 에 저장 (Expiration -> 자동 삭제)
        redisTemplate.opsForValue()
                .set("RT:" + authentication.getName(), tokenDto.getRefreshToken(), tokenDto.getRefreshTokenExpirationTime(), TimeUnit.MILLISECONDS);


        // 5. 토큰 발급
        return tokenDto;
    }

//    @Transactional
//    public TokenDto reissue(TokenRequestDto tokenRequestDto) {  // 토큰 재발급
//        // 1. Refresh Token 만료 여부 검증
//        if (!tokenProvider.validateToken(tokenRequestDto.getRefreshToken())) {
//            throw new CustomException(INVALID_REFRESH_TOKEN);
//        }
//
//        // 2. Access Token 복호화 -> Member ID 가져오기
//        Authentication authentication = tokenProvider.getAuthentication(tokenRequestDto.getAccessToken());
//
//        // 3. 저장소에서 Member ID 를 기반으로 Refresh Token 값 가져옴
//        RefreshToken refreshToken = refreshTokenRepository.findByKey(authentication.getName())
//                .orElseThrow(() -> new CustomException(REFRESH_TOKEN_NOT_FOUND));
//
//        // 4. 클라이언트의 Refresh Token 일치하는지 검사
//        if (!refreshToken.getValue().equals(tokenRequestDto.getRefreshToken())) {
//            throw new CustomException(MISMATCH_REFRESH_TOKEN);
//        }
//
//        // 5. (일치할 경우) 새로운 토큰 생성
//        TokenDto tokenDto = tokenProvider.generateTokenDto(authentication);
//
//        // 6. 저장소 정보 업데이트 (이전의 Refresh Token 을 사용할 수 없도록)
//        RefreshToken newRefreshToken = refreshToken.updateValue(tokenDto.getRefreshToken());
//        refreshTokenRepository.save(newRefreshToken);
//
//        // 토큰 발급
//        return tokenDto;
//    }

    @Transactional
    public TokenDto reissue(TokenRequestDto tokenRequestDto) {  // 토큰 재발급

        // 1. Refresh Token 만료 여부 검증
        if (!tokenProvider.validateToken(tokenRequestDto.getRefreshToken())) {
            throw new CustomException(INVALID_REFRESH_TOKEN);
        }

        // 2. Access Token 복호화 -> Member ID 가져오기
        Authentication authentication = tokenProvider.getAuthentication(tokenRequestDto.getAccessToken());

//        // 3. 저장소에서 Member ID 를 기반으로 Refresh Token 값 가져옴
//        RefreshToken refreshToken = refreshTokenRepository.findByKey(authentication.getName())
//                .orElseThrow(() -> new CustomException(REFRESH_TOKEN_NOT_FOUND));

        // 3. Redis 에서 User email 을 기반으로 저장된 Refresh Token 값을 가져옵니다.
        String refreshToken = (String)redisTemplate.opsForValue().get("RT:" + authentication.getName());
        // (추가) 로그아웃되어 Redis 에 RefreshToken 이 존재하지 않는 경우 처리
        if(ObjectUtils.isEmpty(refreshToken)) {
            throw new CustomException(INVALID_REFRESH_TOKEN);
//            return response.fail("잘못된 요청입니다.", HttpStatus.BAD_REQUEST);
        }

        // 4. 클라이언트의 Refresh Token 일치하는지 검사
        if(!refreshToken.equals(tokenRequestDto.getRefreshToken())) {
            throw new CustomException(MISMATCH_REFRESH_TOKEN);
        }

//        // 4. 클라이언트의 Refresh Token 일치하는지 검사
//        if (!refreshToken.getValue().equals(tokenRequestDto.getRefreshToken())) {
//            throw new CustomException(MISMATCH_REFRESH_TOKEN);
//        }

        // 5. (일치할 경우) 새로운 토큰 생성
        TokenDto tokenDto = tokenProvider.generateTokenDto(authentication);

        // 6. 저장소 정보 업데이트 (이전의 Refresh Token 을 사용할 수 없도록)
        redisTemplate.opsForValue()
                .set("RT:" + authentication.getName(), tokenDto.getRefreshToken(), tokenDto.getRefreshTokenExpirationTime(), TimeUnit.MILLISECONDS);

//        // 6. 저장소 정보 업데이트 (이전의 Refresh Token 을 사용할 수 없도록)
//        RefreshToken newRefreshToken = refreshToken.updateValue(tokenDto.getRefreshToken());
//        refreshTokenRepository.save(newRefreshToken);

        // 토큰 발급
        return tokenDto;
    }

    public SingleResult<String> logout(TokenRequestDto tokenRequestDto) {
        // 1. Access Token 검증
        if (!tokenProvider.validateToken(tokenRequestDto.getAccessToken())) {
            throw new CustomException(INVALID_ACCESS_TOKEN);
        }

        // 2. Access Token 에서 User email 을 가져옵니다.
        Authentication authentication = tokenProvider.getAuthentication(tokenRequestDto.getAccessToken());

        // 3. Redis 에서 해당 User email 로 저장된 Refresh Token 이 있는지 여부를 확인 후 있을 경우 삭제합니다.
        if (redisTemplate.opsForValue().get("RT:" + authentication.getName()) != null) {
            // Refresh Token 삭제
            redisTemplate.delete("RT:" + authentication.getName());
        }

        // 4. 해당 Access Token 유효시간 가지고 와서 BlackList 로 저장하기
        Long expiration = tokenProvider.getExpiration(tokenRequestDto.getAccessToken());
        redisTemplate.opsForValue()
                .set(tokenRequestDto.getAccessToken(), "logout", expiration, TimeUnit.MILLISECONDS);

//        return response.success("로그아웃 되었습니다.");
        return responseService.getSingleResult("로그아웃 되었습니다.");
    }
}
