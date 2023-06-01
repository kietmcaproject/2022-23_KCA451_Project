from django.db import models

# Create your models here.
class Data(models.Model):
    N = models.IntegerField(max_length=3,null=False)
    P = models.IntegerField(max_length=3,null=False)
    K = models.IntegerField(max_length=3,null=False)
    temperature = models.FloatField(max_length=4 , null=False)
    humidity = models.FloatField(max_length=4 , null=False)
    ph = models.FloatField(max_length=4 , null=False)
    rainfall = models.FloatField(max_length=4 , null=False)
    result = models.CharField(max_length=255)
    ml= models.CharField(max_length=255,null=False)

    def __str__(self):
        return f"{self.result}"



class FeedBack(models.Model):
    FirstName= models.CharField(max_length=255,null=False)
    LastName=models.CharField(max_length=255,null=False)
    City=models.CharField(max_length=255,null=False)
    Phone=models.IntegerField(max_length=10)
    Email=models.EmailField(null=False)
    FeedBack=models.CharField(max_length=255,null=False)

    def __str__(self):
        return f"{self.FirstName} {self.LastName}"
