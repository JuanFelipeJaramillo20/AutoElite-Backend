package com.autoelite.AutoElite.registro;

//import com.autoelite.AutoElite.email.EmailSender;
import com.autoelite.AutoElite.Usuarios.Usuario;
import com.autoelite.AutoElite.Usuarios.UsuarioService;
import com.autoelite.AutoElite.email.EmailSender;
import com.autoelite.AutoElite.registro.token.ConfirmationToken;
import com.autoelite.AutoElite.registro.token.ConfirmationTokenService;
//import jakarta.mail.internet.MimeMessage;
import lombok.AllArgsConstructor;
        import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@AllArgsConstructor
public class RegistrationService {

    private final UsuarioService usuarioService;
    private final EmailValidator emailValidator;
    private final ConfirmationTokenService confirmationTokenService;
    private final EmailSender emailSender;


    public String register(RegistrationRequest request) {
        boolean isValidEmail = emailValidator.
                test(request.getEmail());

        if (!isValidEmail) {
            throw new IllegalStateException("email not valid");
        }

        String token = usuarioService.signUpUser(
                new Usuario(
                        request.getId(),
                        request.getNombres(),
                        request.getEmail(),
                        request.getTelefono(),
                        request.getContrasena(),
                        request.getRol()
                )
        );

        String link = "http://localhost:8080/api/v1/registro/confirm?token=" + token;
        emailSender.send(
                request.getEmail(),
                buildEmail(request.getNombres(), link));

        return token;
    }

    @Transactional
    public String confirmToken(String token) {
        ConfirmationToken confirmationToken = confirmationTokenService
                .getToken(token)
                .orElseThrow(() ->
                        new IllegalStateException("token not found"));

        if (confirmationToken.getConfirmedAt() != null) {
            throw new IllegalStateException("email already confirmed");
        }

        LocalDateTime expiredAt = confirmationToken.getExpiresAt();

        if (expiredAt.isBefore(LocalDateTime.now())) {
            throw new IllegalStateException("token expired");
        }

        confirmationTokenService.setConfirmedAt(token);
        usuarioService.isEnableUsuario(
                confirmationToken.getUsuario().getEmail());
        return "confirmed";
    }

    private String buildEmail(String name, String link) {
        return "<p> Hi, "+ name+ ", </p>"+
                "<p>Gracias por registrarte con nosotros,"+"" +
                "Por favor, siga el siguiente enlace para completar su inscripción.</p>"+
                "<a href=\"" +link+ "\">verifica tu email para activar tu cuenta</a>"+
                "<p> Gracias <br> Users Registration Portal Service";
    }
}
