from concurrent import futures
import grpc
import calculator_pb2 as Action
import calculator_pb2_grpc as Service

class Calculator(Service.CalculatorServicer):

    def Add(self, request, context):
        risultato = request.opA + request.opB
        return Action.OperationResponse(res=risultato)

    def Subtract(self, request, context):
        risultato = request.opA - request.opB
        return Action.OperationResponse(res=risultato)

    def Multiply(self, request, context):
        risultato = request.opA * request.opB
        return Action.OperationResponse(res=risultato)

    def Divide(self, request, context):
        if request.opB == 0:
            context.set_code(grpc.StatusCode.INVALID_ARGUMENT)
            context.set_details('Divisione per zero!')
            return Action.OperationResponse()
        risultato = int(request.opA / request.opB)
        return Action.OperationResponse(res=risultato)

def serve():
    port = "50077"
    server = grpc.server(futures.ThreadPoolExecutor(max_workers=10))
    Service.add_CalculatorServicer_to_server(Calculator(), server)
    server.add_insecure_port("localhost:"+port)
    server.start()
    print(f"Listening on port {port}")
    server.wait_for_termination()

if __name__ == "__main__":
    serve()