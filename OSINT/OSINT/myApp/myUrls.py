from django.urls import path
from . import views

urlpatterns = [
    path('', views.home_page, name='home_page'),
    path('data', views.form_data, name='data'),
    path('csv', views.csv, name='csv')
]