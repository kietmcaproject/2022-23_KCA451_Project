from django.urls import path
from .views import DataView,CreateDataView,CreateFeedBackView
urlpatterns = [

    path('history',DataView.as_view()),
    path('recommend',CreateDataView.as_view()),
    path('feedback',CreateFeedBackView.as_view())

]