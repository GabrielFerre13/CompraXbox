# language:pt
Funcionalidade: Compra de jogo no Xbox Store
  Como um usuário do Xbox
  Quero selecionar e comprar um jogo
  Para poder jogá-lo na minha conta

  Contexto: Login no site
    Dado que estou acessando o site
    Quando eu informo usuario "gf2036@outlook.com"
    E a senha "Real1020"
    Entao devo ser redirecionado para pagina inicial

    Cenário: Pesquisa de Jogos
      Dado que estou logado na minha conta
      Quando eu pesquisar jogo "Forza Horizon 5 Edição Padrão"
      Entao deve aparecer o jogo
      E compro ele
      E clico em avanca
      Entao vai parecer as formas de pagamento





