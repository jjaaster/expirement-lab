from selenium import webdriver
from selenium.webdriver.common.keys import Keys
import time

config = {
    'EMAIL': '',
    'PASSWORD': ''
}

login_url = 'http://video.springserve.com/'

def main():
    driver = webdriver.Chrome('./chromedriver')
    driver.get(login_url)
    assert 'SpringServe' in driver.title
    elem = driver.find_element_by_name('user[email]')
    elem.clear()
    elem.send_keys(config['EMAIL'])
    elem = driver.find_element_by_name('user[password]')
    elem.clear()
    elem.send_keys(config['PASSWORD'])
    elem.send_keys(Keys.RETURN)

    time.sleep(5)

    data = {}

    summary = driver.find_element_by_xpath('//*[@id="account_summary"]/ul/li[1]/span').text
    data['Accounty Summary'] = summary

    print(data)

    driver.close()

if __name__ == '__main__':
    main()