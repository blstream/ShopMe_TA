#!/usr/bin/env bash
chrome_driver_version="2.36"
download_url_win="https://chromedriver.storage.googleapis.com/$chrome_driver_version/chromedriver_win32.zip"
download_url_mac="https://chromedriver.storage.googleapis.com/$chrome_driver_version/chromedriver_mac64.zip"
mkdir -p drivers/$chrome_driver_version
cd drivers/$chrome_driver_version && { curl -O $download_url_win ; cd -; }
cd drivers/$chrome_driver_version && { curl -O $download_url_mac ; cd -; }
cd drivers/$chrome_driver_version
unzip \*.zip
rm *.zip