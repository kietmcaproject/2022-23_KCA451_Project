from django.contrib import admin
from .models import Data,FeedBack
# Register your models here.
class FeedBackAdmin(admin.ModelAdmin):
    list_display = ( "FirstName",
    "LastName",
    "City",
    "Phone",
    "Email",
    "FeedBack")
admin.site.register(Data)
admin.site.register(FeedBack,FeedBackAdmin)
