import grpc
import calculator_pb2 as Action
import calculator_pb2_grpc as Service


def run():
    with grpc.insecure_channel('localhost:50077') as channel:
        stub = Service.CalculatorStub(channel)
        request = Action.OperationRequest(opA=1, opB=2)
        response = stub.Add(request)
        print(f"Risultato ricevuto dal server: {response.res}")

if __name__ == '__main__':
    run()