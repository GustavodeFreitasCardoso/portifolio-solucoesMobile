//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.

data class Requisito(
    val mensagemErro: String,
    val validacao :(String) -> Boolean
)
fun validarSenha(senha: String , requisitos:
List<Requisito>): String? {
    for (Requisito in requisitos) {
        if (!Requisito.validacao(senha)) {
            return Requisito.mensagemErro
        }
    }
    return null
}
fun main() {
    val requisitos = listOf(

        Requisito(
            mensagemErro = "A senha deve ter pelomenos 5 caracteres."
        ) { senha ->
            senha.length >= 5
        },
        Requisito(
            mensagemErro = "A senha deve conter pelomenos uma letra maiuscula."
        ) { senha ->
            senha.any { it.isUpperCase() }
        },
        Requisito(
            mensagemErro = "A senha deve conter pelo menos um numero."
        ) { senha ->
            senha.any { it.isDigit() }
        },
        Requisito(
            mensagemErro = "A senha deve conter a palavra 'flamengo'."
        ) { senha ->
            senha.lowercase().contains("flamengo")
        },
        Requisito(
            mensagemErro = "A senha deve conter o ano do penta mundial do Brasil."
        ) { senha ->
            senha.contains("2002")
        },
        Requisito(
            mensagemErro = "A senha deve conter pelomenos um numero primo (2,3,5,7)."
        ) {senha ->
            val primos = listOf('2','3','5','7')
            senha.any { it in primos }
        },
        Requisito(
            mensagemErro = "A senha deve conter exatamente 25 caracteres."
        ) {senha ->
            senha.length == 25
        }

        )
    var senha: String
    var erro : String?

    do {
        print("\nDigite sua senha: ")
        senha = readLine() ?: ""

        erro = validarSenha(senha,requisitos)

        if (erro != null) {
            println("X $erro")
        } else {
            println("Senha valida ! você criou a senha absoluta !")
        }
    } while (erro != null)
}
