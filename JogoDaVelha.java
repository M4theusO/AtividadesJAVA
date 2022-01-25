import javax.swing.JOptionPane;

public class JogoDaVelha {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
	
		String jogador = "";
		int acaba = 0;
		int venceu = 0;
		int contador = 0;
		String [][] matriz = new String [3][3];
		int posicao = 0;
		int [] posicaoOcupado = new int [9];
		
		int resposta = Integer.parseInt(JOptionPane.showInputDialog("Você deseja jogar o jogo da velha? 1 - Sim ou 2 - Não"));	
		
		while(resposta == 1) {
			iniciaVisual(matriz);
			do {
				jogador = escolheJogador(contador++); //coloquei ++ e funfou PQ?
				posicao = validaPosicao(jogador,posicaoOcupado);
					
				escolhePosicao(jogador,posicao,matriz);
					
				mostraPosicao(matriz);
					
					venceu = verificaVencedor(matriz,jogador,venceu);					
			      	acaba = mostraGanhador(venceu,jogador);			      	
			      	acaba = verificaVelha(contador,acaba);	
			      	
			}while(acaba == 0);						
		resposta = Integer.parseInt(JOptionPane.showInputDialog("Você quer jogar novamente o 'JOGO DA VELHA'? (1-SIM ou 2-NÃO) "));
		//reset
		jogador = "";
		acaba = 0;
		venceu = 0;
		contador = 0;
		posicao = 0;
		posicaoOcupado = new int [9];
			for(int l = 0; l < 3; l++) {  //preencher matriz "?"
				for(int c = 0; c < 3; c++) {
					matriz[l][c] = "?";
				}
			}
		}
		
	}
	
	public static void iniciaVisual(String [][] matriz) {
		
		JOptionPane.showMessageDialog(null,"BEM VINDO(A) AO JOGO DA VELHA!\n\n"
				  + "     1     |    2    |    3   \n" +  
					"=====|=====|=====\n" +  
					"     4     |    5    |    6   \n" +  
					"=====|=====|=====\n" + 
					"     7     |    8    |    9   \n");

		for(int l = 0; l < 3; l++) {  //preencher matriz com "?"
			for(int c = 0; c < 3; c++) {
				matriz[l][c] = "?";
			}
		}
	}
	
	public static String escolheJogador(int contador) {
		
		String jogador = "";
		
		if(contador%2!=1) { //primeira vez será par e será o 'X'
			jogador = "X";
		}else {
			jogador = "O";
		}
		return jogador;
	}
	
	public static int validaPosicao(String jogador,int posicaoOcupado[]) {
		
		int posicao = Integer.parseInt(JOptionPane.showInputDialog("Jogador " + jogador + " informe uma posição: "));
		posicao = posicao-1; //usuario informa de 1 à 9, porém
		while(posicao < 0 || posicao > 8 || posicaoOcupado[posicao] == 1) {
			JOptionPane.showMessageDialog(null,"A posição informada já foi usada ou não está entre 1 e 9!");
			posicao = Integer.parseInt(JOptionPane.showInputDialog("Jogador " + jogador + " informe uma posição válida: "));
			posicao = posicao-1; //usuario informa de 1 à 9, porém
		}
		posicaoOcupado[posicao] = 1;
		return posicao;
	}
	
	public static String [][] escolhePosicao(String jogador, int posicao, String [][] matriz) {
		
		switch(posicao) { //fiz de 0 à 8 para poder definir a posição no método
		case 0:
			matriz[0][0] = jogador;
		break;
		case 1:
			matriz[0][1] = jogador;
		break;
		case 2:
			matriz[0][2] = jogador;
		break;
		case 3:
			matriz[1][0] = jogador;
		break;
		case 4:
			matriz[1][1] = jogador;
		break;
		case 5:
			matriz[1][2] = jogador;
		break;
		case 6:
			matriz[2][0] = jogador;
		break;
		case 7:
			matriz[2][1] = jogador;
		break;
		case 8:
			matriz[2][2] = jogador;
		break;					
		}
		return matriz;
	}
	
	public static void mostraPosicao(String [][] matriz) {
		
		JOptionPane.showMessageDialog(null,""
			      + "    "+matriz[0][0]+"    |    "+matriz[0][1]+"    |   "+matriz[0][2]+"  \n"
			      +"=====|=====|====\n"
			      +"    "+matriz[1][0]+"    |    "+matriz[1][1]+"    |   "+matriz[1][2]+"  \n"
			      +"=====|=====|====\n"
			      +"    "+matriz[2][0]+"    |    "+matriz[2][1]+"    |   "+matriz[2][2]+"  \n");
	}
	
	public static int verificaVencedor(String matriz[][],String jogador,int venceu) {
		
		for(int contadora = 0; contadora < 3; contadora++) { //utilizei a var criada para determinar quem joga, para fazer a validação
      		if(matriz[contadora][0] == jogador && matriz[contadora][1] == jogador && matriz[contadora][2] == jogador){
      			venceu = 1;
      		}
      		if(matriz[0][contadora]==jogador && matriz[1][contadora]==jogador && matriz[2][contadora]==jogador){
      			venceu = 1;
      		}
      		if(matriz[0][0]==jogador && matriz[1][1]==jogador && matriz[2][2]==jogador || matriz[2][0]==jogador && matriz[1][1]==jogador && matriz[0][2]==jogador){
      			venceu = 1;
      		}
      	}
		return venceu;
	}
	
	public static int mostraGanhador(int venceu,String jogador) {
		
		int acaba = 0;
		if(venceu==1) { //se alguem vencer. E utilizei a var que determina quem joga novamente para passar a msg sem ficar repetindo
      		JOptionPane.showMessageDialog(null,"Jogador(a) '" + jogador + "' venceu!");
      		acaba = 1;
      	}
		return acaba;
	}
	
	public static int verificaVelha(int contador,int acaba) { 
		
		if(contador==9 && acaba==0) {//para finalizar a estrutura de repetição
    	  	JOptionPane.showMessageDialog(null,"DEU VELHA!");
       	 	acaba = 1;
      	}
		return acaba;
	}
	
}