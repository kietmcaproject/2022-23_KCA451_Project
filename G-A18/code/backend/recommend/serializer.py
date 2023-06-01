from rest_framework import serializers
from .models import Data,FeedBack

class DataSerializer(serializers.ModelSerializer):
    class Meta:
        model=Data
        fields=('id','N','P','K','temperature','humidity','ph','rainfall', 'result','ml')


class CreateDataSerializer(serializers.ModelSerializer):
    class Meta:
        model=Data
        fields=('id','N','P','K','temperature','humidity','ph','rainfall', 'result','ml')

class CreateFeedBackSerializer(serializers.ModelSerializer):
    class Meta:
        model=FeedBack
        fields=('id','FirstName','LastName','city','phoneno','Email','Feedback')