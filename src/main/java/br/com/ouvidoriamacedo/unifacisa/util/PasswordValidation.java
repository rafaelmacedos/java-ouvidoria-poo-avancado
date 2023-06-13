package br.com.ouvidoriamacedo.unifacisa.util;

import java.util.regex.Pattern;

public abstract class PasswordValidation {
    // Senha de 4-32 caracteres exigindo pelo menos 3 de 4 (maiúsculas
    // e letras minúsculas, números e caracteres especiais) e no máximo
    // 2 caracteres consecutivos iguais.
    private static final String COMPLEX_PASSWORD_REGEX =
            "^(?:(?=.*\\d)(?=.*[A-Z])(?=.*[a-z])|" +
            "(?=.*\\d)(?=.*[^A-Za-z0-9])(?=.*[a-z])|" +
            "(?=.*[^A-Za-z0-9])(?=.*[A-Z])(?=.*[a-z])|" +
            "(?=.*\\d)(?=.*[A-Z])(?=.*[^A-Za-z0-9]))(?!.*(.)\\1{2,})" +
            "[A-Za-z0-9!~<>,;:_=?*+#.\"&§%°()\\|\\[\\]\\-\\$\\^\\@\\/]" +
            "{8,32}$";
 
    private static final Pattern PASSWORD_PATTERN =
                                    Pattern.compile(COMPLEX_PASSWORD_REGEX);
 
    
    public static boolean validarSenha(String senha) {
        // Valida uma senha
        if (PASSWORD_PATTERN.matcher(senha).matches()) {
            return true;
        }
        else {
            System.out.print("\nA senha " + senha + " não é válida");
            return false;
        }
    }
}
