import pathlib
import urllib
from django.http.response import HttpResponse
from django.shortcuts import render
from urllib.parse import urlparse
import requests
import os
from csv import writer
from myApp.analyse.analyste import analyse



# my lines
def home_page(request):
    return render(request, 'myApp/home.html')

def form_data(request):
    save_path='./extracted_pages' #path where to save pages
    
    regex = request.POST.get("regex")
    url = request.POST.get("url")   #get the url from the html input
    url_hostname=urlparse(url).hostname  #get hostname
    format_file = ".txt"
    file_name = url_hostname+format_file
    complete_file_path=os.path.join(save_path,file_name)

    page = requests.get(url,'html.parser')

    url_content=open(complete_file_path,'w')

    url_content.write(page.text)
    url_content.close()

    results = analyse(regex,complete_file_path)
    print("----------> results: " + str(results))

    ### CSV
    with open(os.path.join(save_path,url_hostname+'.csv'), 'w', newline='') as csvfile:
        #spamwriter = writer(csvfile, delimiter=',')
        for r in results:
            csvfile.write(r+'\n')
            #spamwriter.writerows(r)

    return render(request, 'myApp/results.html', { "results": results, "name": url_hostname})

def csv(request):
    save_path='./extracted_pages'
    csv_filename = request.GET.get('name') + '.csv'

    with open(os.path.join(save_path,csv_filename)) as csvfile:
        response = HttpResponse(csvfile, content_type='text/csv')
        response['Content-Disposition'] = 'attachment; filename=' + csv_filename
        return response
