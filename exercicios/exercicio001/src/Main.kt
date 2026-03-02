//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
fun main() {
    // 1. Definição da lista de requisitos
     val listaDeRegras = listOf(
         Requisito("Mínimo de 5 caracteres") { it.length >= 5 },
         Requisito("Deve conter o ano do Hexa (2026)") { it.contains("2026") }
         // Adicione as demais regras e as suas 3 criações extras aqui
         )

     var senhaAprovada = false

     // 2. Loop de tentativa e erro
     do {
         println("\nDigite sua senha:")
         val entrada = readLine() ?: "" // Null Safety com operador Elvis
         var erroEncontrado: String? = null

        for (regra in listaDeRegras) {
              if (!regra.validacao(entrada)) {
                 erroEncontrado = regra.mensagemErro
                 break
                 }
             }
         if (erroEncontrado != null) {
             println("❌ ERRO: $erroEncontrado")
             } else {
             println("✅ SUCESSO! Senha aceite pelo Overlord.")
             senhaAprovada = true
             }
        } while (!senhaAprovada)
    }