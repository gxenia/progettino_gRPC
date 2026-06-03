package ese.ingsw.rubrica.server;

import ese.ingsw.rubrica.ContactList;
import ese.ingsw.rubrica.ListRequest;
import ese.ingsw.rubrica.NomeContatto;
import ese.ingsw.rubrica.Person;
import ese.ingsw.rubrica.Result;
import ese.ingsw.rubrica.RubricaGrpc;
import io.grpc.stub.StreamObserver;

import java.util.concurrent.ConcurrentHashMap;

public class RubricaServiceImpl extends RubricaGrpc.RubricaImplBase {

    private ConcurrentHashMap<String, Person> contacts = new ConcurrentHashMap<>();

    @Override
    public void addContact(Person request, StreamObserver<Result> responseObserver) {
        Person person = contacts.put(request.getNome(), request);
        if (person == null) {
            responseObserver.onNext(null);
            responseObserver.onCompleted();
        }
        Result res = Result.newBuilder().setCreato(true).build();
        responseObserver.onNext(res);
        responseObserver.onCompleted();
    }

    @Override
    public void getContact(NomeContatto request, StreamObserver<Person> responseObserver) {
        Person person = contacts.get(request.getNomeContatto());
        if (person == null) {
            responseObserver.onNext(null);
        }
        responseObserver.onNext(person);
        responseObserver.onCompleted();
    }

    @Override
    public void getAll(ListRequest request, StreamObserver<ContactList> responseObserver) {
        ContactList res = ContactList.newBuilder()
                .addAllPerson(contacts.values())
                .build();
        responseObserver.onNext(res);
        responseObserver.onCompleted();
    }
}
