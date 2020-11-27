import javax.swing.text.TabableView;

class Word{
    public String Palavras[] = {
        "Abacaxi", "Manada", "mandar", "porta", "mesa", "Dado", "Mangas", "Já", "coisas", "radiografia","matemática", "Drogas", "prédios", "implementação", "computador", "balão", "Xícara", "Tédio","faixa", "Livro", "deixar", "superior", "Profissão", "Reunião", "Prédios", "Montanha", "Botânica","Banheiro", "Caixas", "Xingamento", "Infestação", "Cupim", "Premiada", "empanada", "Ratos","Ruído", "Antecedente", "Empresa", "Emissário", "Folga", "Fratura", "Goiaba", "Gratuito","Hídrico", "Homem", "Jantar", "Jogos", "Montagem", "Manual", "Nuvem", "Neve", "Operação","Ontem", "Pato", "Pé", "viagem", "Queijo", "Quarto", "Quintal", "Solto", "rota", "Selva",
        "Tatuagem", "Tigre", "Uva", "Último", "Vitupério", "Voltagem", "Zangado", "Zombaria", "Dor","uva"};
    public String tabela[][] = {
        {"1","E","e", "A","a", "I","i", "O","o", "N","n", "R","r", "T","r", "L","l", "S","s", "U","u"},
        {"2","W","w", "D","d", "G","g"},
        {"3","B","b", "C","c", "M","m", "P","p"},
        {"4","F","f", "H","h", "V","v"},
        {"8","J","j", "X","x"},
        {"10","Q","q", "Z","z"}};
    public char letras[];
    public char invalid_letter[];
    public char valid_letter[];
    public String valid_words[];

    public Word(String let){
        this.letras = new char[let.length()];
        this.invalid_letter = new char[let.length()];
        this.valid_letter = new char[let.length()];
        this.valid_words = new String[Palavras.length];
        split_word(let);
    }

    public char[] get_letras(){
        return letras;
    }
    public char[] get_inval(){
        return invalid_letter;
    }
    public char[] get_val(){
        return valid_letter;
    }
    public String[] get_valid_words(){
        return valid_words;
    }

    public void split_word(String let){
        for(int i=0;i<let.length();i++){
            letras[i]=let.charAt(i);
        }
    }

    public void letters_verification(){
        boolean val = false;
        int c1=0,c2=0;
        //Rodando vetor de letras fornecido
        for(int i=0;i<letras.length;i++){
            // Verificando com cada letra da tabela de validacao
            for(int a=0;a<tabela.length;a++){
                // Começando a busca na posição 1 para pularmos o peso
                for(int b=1;b<tabela[a].length;b++){
                    if(letras[i]==tabela[a][b].charAt(0)){
                        // System.out.println("Letras true: "+letras[i]);
                        // System.out.println("tabela: "+tabela[a][b].charAt(0));
                        val = true;
                    }
                }
            }
            if(val==false){
                invalid_letter[c1]=letras[i];
                c1++;
            }else{
                valid_letter[c2] = letras[i];
                c2++;
            }
            val=false;
                
        }
    }

    public void word_verification(){
        // Passando por todas as possiveis palavras
        int validacao=0;
        int c1=0;
        for(int i=0;i<Palavras.length;i++){
            // Passando por cada letra da palavra
            for(int j=0;j<Palavras[i].length()-1;j++){
                // Para cada letra da palavra, verificar se ha correspondencia no vetor de letras validadas
                // System.out.println("testando palavra\n==========================================: "+Palavras[i]);
                // System.out.println("testando letra ======: "+Palavras[i].charAt(j));
                for(int k=0;k<valid_letter.length;k++){
                    // System.out.println("testando letra valida: "+valid_letter[k]);
                    if(valid_letter[k]==Palavras[i].charAt(j)){
                        // System.out.println("found: "+valid_letter[k]+" e "+Palavras[i].charAt(j));
                        validacao++;
                        k = valid_letter.length;
                        // System.out.println("validação"+validacao);
                    }
                }
            }
            // System.out.println("validação = "+validacao+" e tamanho da palavra = "+Palavras[i].length());
            if(validacao==Palavras[i].length()){
                valid_words[c1]=Palavras[i];
                c1++;
            }
            validacao = 0;

        }
    }

    public String get_best_word_pontuation(){
        int actual_pontuation=0,best_pontuation=0;
        String best_word="";
        // Passando por todas as palavras selecionadas
        for(int i=0;i<valid_words.length;i++){
            // Passando por cada letra da palavra para calcular os pontos 
            if(valid_words[i]!=null){
                for(int j=0;j<valid_words[i].length()-1;j++){
                    // Linha da tabela de pontos
                    for(int k=0;k<tabela.length;k++){
                        // Colunas da tabela de pontos
                        for(int l=1;l<tabela[k].length;l++){
                            if(tabela[k][l].charAt(l)==valid_words[i].charAt(j)){
                                actual_pontuation = actual_pontuation + Integer.parseInt(tabela[k][0]);
                            }
                        }
                    }
                }

                if(actual_pontuation>best_pontuation){
                    best_pontuation = actual_pontuation;
                    best_word = valid_words[i];
                }else{
                    if(actual_pontuation == best_pontuation){
                        if(best_word.length()>valid_words[i].length()){
                            best_word = valid_words[i];
                        }
                    }
                }
            }
        }
        System.out.println(best_word+" "+best_pontuation);
        return (best_word+" "+best_pontuation);
    }

}


public class make_word {
    public static void main(String[] args) {
        String h = "Uvausçufábàsi7wu~[ aárdufbd";
        Word oi = new Word(h);
        char [] tip = oi.get_letras();
        char [] inval = oi.get_inval();
        char [] val = oi.get_val();
        String[] word = oi.get_valid_words();
        oi.letters_verification();
        oi.word_verification();
        //oi.get_best_word_pontuation();
        
        
        System.out.print("INVAL  ");
        for(int i=0;i<h.length();i++){
            if(inval[i] != ' ')
                System.out.println(inval[i]);
        }
        System.out.print("VAL  ");
        for(int i=0;i<h.length();i++){
            if(val[i] != ' ')
                System.out.println("VAL "+val[i]);
        }
        for(int i=0;i<word.length;i++){
            if(word[i] != " " && word[i] != null )
                System.out.println("Palavra: "+word[i]);
        }
    }
}