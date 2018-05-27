package aoj.djj.exemplo01;

import aoj.djj.core.Game;

public class Main {

    public static void main(String[] args) {
        // A execução do programa Java começa aqui, no método estático main.
        // Tudo o que fazemos aqui é criar um objeto do tipo JogoCirculo e 
        // colocar ele para rodar.
        // Abra o arquivo JogoCirculo.java e veja seu código.
        Game myGame = new JogoCirculo();
        myGame.run();
        // Quando o jogo terminar de rodar, chegaremos aqui e o programa termina.
    }
}
