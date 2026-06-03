package ese.ingsw.rubrica.client;

import com.google.rpc.context.AttributeContext;
import ese.ingsw.rubrica.*;
import ese.ingsw.rubrica.ContactList;
import ese.ingsw.rubrica.ListRequest;
import ese.ingsw.rubrica.Person;
import ese.ingsw.rubrica.Result;
import ese.ingsw.rubrica.RubricaGrpc;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

public class RubricaClient {

    public static void main(String[] args) {
        ManagedChannel channel = ManagedChannelBuilder
                .forAddress("localhost", 50067)
                .usePlaintext()
                .build();

        RubricaGrpc.RubricaBlockingStub stub = RubricaGrpc.newBlockingStub(channel);

        Person p = Person.newBuilder().setId(1).setNome("Marta").setTelefono("123").build();
        Result r = stub.addContact(p);

        ListRequest l = ListRequest.newBuilder().build();
        ContactList c = stub.getAll(l);

        System.out.println("Risultato: " + c);

        channel.shutdown();
    }
}
