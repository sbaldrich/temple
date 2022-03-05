import java.io.*;
import java.util.*;

class Main {

	public static void main(String[] args){
		ContestIO io = ContestIO.get("__input.txt");
        // Solve here
		io.out.close();
	}

	static class ContestIO {
		
		PrintWriter out = new PrintWriter(System.out);
		BufferedReader reader;

		StringTokenizer tokenizer;

		ContestIO(Reader reader) {
			this.reader = new BufferedReader(reader);
		}

		static ContestIO get(){
			return new ContestIO(new InputStreamReader(System.in));
		}

		static ContestIO get(String file){
			try{
				return new ContestIO(new FileReader(file));
			} catch(IOException ex){
				return ContestIO.get();
			}
		}

		String readLine(){
			try {
				return reader.readLine();
			} catch (IOException e) {
				throw new RuntimeException("Couldn't read anything", e);
			}
		}

		String next(){
			while(tokenizer == null || ! tokenizer.hasMoreElements()){
				tokenizer = new StringTokenizer(readLine());
			}
			return tokenizer.nextToken();
		}

		int nextInt(){
			return Integer.parseInt(next());
		}

		long nextLong(){
			return Long.parseLong(next());
		}
	}

}

