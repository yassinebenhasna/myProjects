import os
import re

def analyse(regex,complete_file_path):
    # The folder contins all extracted pages
    if len(regex) == 0:
        ip = "(?:[0-9]{1,3}\.){3}[0-9]{1,3}"
        email = "[a-zA-Z0-9_.+-]+@[a-zA-Z0-9-]+\.[a-zA-Z0-9-.]+"
        url_list ="/(https?:\/\/(?:www\.|(?!www))[a-zA-Z0-9][a-zA-Z0-9-]+[a-zA-Z0-9]\.[^\s]{2,}|www\.[a-zA-Z0-9][a-zA-Z0-9-]+[a-zA-Z0-9]\.[^\s]{2,}|https?:\/\/(?:www\.|(?!www))[a-zA-Z0-9]+\.[^\s]{2,}|www\.[a-zA-Z0-9]+\.[^\s]{2,})/gi"
        # dd/mm/yyyy, dd-mm-yyyy or dd.mm.yyyy
        #date = "(0?[1-9]|[12]\d|30|31)[^\w\d\r\n:](0?[1-9]|1[0-2])[^\w\d\r\n:](\d{4}|\d{2})"
        #phoneNumber = "(\+?(\d{1,3}))?[-. (]*(\d{3})[-. )]*(\d{3})[-. ]*(\d{4})"

        regexList = [ip, email, url_list]
        
    else:
        regexList = [regex]


    finalResults = []
    with open(complete_file_path) as f:
        for line in f:
            for regex in regexList:
                resultsFounded = re.findall(regex, line) 
                if len(resultsFounded) != 0:
                    finalResults += resultsFounded
    f.close()
    return finalResults