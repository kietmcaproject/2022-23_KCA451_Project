from django.http import JsonResponse
from rest_framework import generics,status
from .models  import Data,FeedBack
from .serializer import DataSerializer,CreateDataSerializer,CreateFeedBackSerializer
from rest_framework.response import Response
from rest_framework.views import APIView
import pickle
import sklearn
import numpy as np
import pandas as pd



# Create your views here.
class DataView(generics.ListAPIView):
    queryset = Data.objects.all()
    serializer_class = DataSerializer

class CreateDataView(APIView):
    serializer=CreateDataSerializer
    def post(self,request):
        infile = open('encoder', 'rb')
        encoder = pickle.load(infile)
        infile.close()

        infile = open('accuracy', 'rb')
        accuracy = pickle.load(infile)
        infile.close()

        infile = open('classifier', 'rb')
        classifier = pickle.load(infile)
        infile.close()



        request.data['N']=int(request.data['N'])
        request.data['P'] = int(request.data['P'])
        request.data['K'] = int(request.data['K'])
        request.data['temp'] = float(request.data['temp'])
        request.data['Ph'] = float(request.data['Ph'])
        request.data['humidity'] = float(request.data['humidity'])
        request.data['rain'] = float(request.data['rain'])

        print(classifier)
        pred=classifier[ request.data['classifier']]
        print(pred)
        data = [request.data['N'],request.data['P'],request.data['K'],request.data['temp'],request.data['Ph'],request.data['humidity'],request.data['rain']]
        input = np.array(data)
        input = input.reshape(1, 7)
        feature_name = ["N", "P", "K", "temperature", "humidity", "ph", "rainfall"]
        c = pd.DataFrame(input, columns=feature_name)
        from sklearn.preprocessing import LabelEncoder

        file=pd.read_csv('Crop_recommendation.csv')
        label=LabelEncoder()
        label.fit_transform(file['label'])
        file['label']=label.fit_transform(file['label'])
        print(encoder.inverse_transform(pred.predict(c)))
        



        print(label.inverse_transform(pred.predict(c)))
        ans = label.inverse_transform(pred.predict(c))
        request.data['result'] = ans
        model={'rf':"RandomForestCassifier",
            'knn':"KNNClassifier",
            'dt':"DecissionTreeClassifier"}

        data=Data(N=request.data['N'],P=request.data['P'],K=request.data['K'],temperature=request.data['temp'],humidity=request.data['humidity'],ph=request.data['Ph'],rainfall=request.data['rain'],result=ans,ml=model[request.data['classifier']])
        data.save()
        d=[accuracy[request.data['classifier']],str(ans),model[request.data['classifier']]]

        return JsonResponse(d,safe=False)


class CreateFeedBackView(APIView):

    def post(self,request):
        model=FeedBack(FirstName=request.data['first'], LastName=request.data['last'] ,City= request.data['city'], Phone=request.data['phone'],Email= request.data['email'], FeedBack=request.data['feedback'])
        model.save()
        return JsonResponse({'as':2})