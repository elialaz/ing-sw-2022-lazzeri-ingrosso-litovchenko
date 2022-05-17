package it.polimi.ingsw.Server;

import java.io.*;
import java.net.Socket;

class ServerThread extends Thread{
    int id;
    String line;
    BufferedReader is;
    PrintWriter os;
    Socket s;

    public ServerThread(Socket s, ServerEventManager eventManager, int id){
        this.id = id;
        line = null;
        this.s = s;
        try{
            is = new BufferedReader(new InputStreamReader(s.getInputStream()));
            os = new PrintWriter(s.getOutputStream());
        }catch(IOException e){
            System.out.println("IO error in server thread");
        }
    }

    public void run() {
        try {
            line=is.readLine();
            while(line.compareTo("QUIT")!=0){

                os.println(line);
                os.flush();
                System.out.println("Response to Client  :  "+line);
                line=is.readLine();
            }
        } catch (IOException e) {

            line=this.getName(); //reused String line for getting thread name
            System.out.println("IO Error/ Client "+line+" terminated abruptly");
        }
        catch(NullPointerException e){
            line=this.getName(); //reused String line for getting thread name
            System.out.println("Client "+line+" Closed");
        }

        finally{
            try{
                System.out.println("Connection Closing..");
                if (is!=null){
                    is.close();
                    System.out.println(" Socket Input Stream Closed");
                }

                if(os!=null){
                    os.close();
                    System.out.println("Socket Out Closed");
                }
                if (s!=null){
                    s.close();
                    System.out.println("Socket Closed");
                }

            }
            catch(IOException ie){
                System.out.println("Socket Close Error");
            }
        }//end finally
    }
}