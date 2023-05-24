import time
from webbrowser import Chrome
from selenium import webdriver
from selenium.webdriver.common.keys import Keys


driver = webdriver.Chrome('C:\Program Files (x86)\chromedriver.exe')

driver.get("https://www.Google.com")
driver.get("https://www.Github.com")
driver.get("https://www.Github.com/login")

#s = Service(chrome_path)
#op = Options()
#with webdriver.Chrome(service=s, options=op) as d:

driver.find_element_by_name("s")
driver.send_keys("test")
driver.send_keys(Keys.RETURN)

driver.submit()

######################
######################




