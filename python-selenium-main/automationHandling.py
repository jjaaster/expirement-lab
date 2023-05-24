# import modules
from selenium import webdriver
import time

driver = webdriver.Chrome('C:\Program Files (x86)\chromedriver.exe')

# assign URL
driver.get("https://www.google.com")
print("First window title = " + driver.title)

# switch to new window
#driver.find_element_by_class_name("privacy").click()
#print(driver.window_handles)
#driver.switch_to.window(driver.window_handles[1])
#rint("Second window title = " + driver.title)

# switch to new window
driver.execute_script("window.open()")
print(driver.window_handles)
driver.switch_to.window(driver.window_handles[1])
driver.get("https://www.nsa.gov/")
print(driver.title)
