
dump_path="/home/mulga/Dropbox/muninn/cbr/$(date +%G)/$(date +%m)"
dump_filename="$dump_path/$(date +%d).xml"
echo $dump_path
echo $dump_filename

mkdir -p $dump_path
curl http://www.cbr.ru/scripts/XML_daily_eng.asp > $dump_filename

