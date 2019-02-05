import urllib.request
from bs4 import BeautifulSoup


def get_html(url):
	return urllib.request.urlopen(url).read()


if __name__ == '__main__':
	html = get_html('https://www.ebay.com/str/0homveiuhw')
	soup = BeautifulSoup(html, features='html.parser')
	for item in soup.find_all(attrs={'class': 's-item__link'}):
		item_url = item['href']
		item_html = get_html(item_url)
		item_soup = BeautifulSoup(item_html, features='html.parser')
		for items_sold in item_soup.find_all(attrs={'class': 'vi-qty-pur-lnk'}):
			print(items_sold.find('a').string + ': ' +  item_url)