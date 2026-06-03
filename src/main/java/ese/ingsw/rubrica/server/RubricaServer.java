package ese.ingsw.rubrica.server;

import io.grpc.Server;
import io.grpc.ServerBuilder;

import java.io.IOException;

public class RubricaServer {
    static final int PORT = 50067;

    public static void main(String[] args) throws IOException, InterruptedException {
        Server server = ServerBuilder
                .forPort(PORT)
                .addService( new RubricaServiceImpl() )
                .build()
                .start();

        System.out.println("Server gRPC avviato sulla porta " + PORT);
        System.out.println("In ascolto di chiamate remote...");

        // Chiusura elegante all'interrupt
        Runtime.getRuntime().addShutdownHook(new Thread(server::shutdown));
        server.awaitTermination();
    }
}
