localIPRange=$(ip addr show wlp2s0 |grep -oP 'inet \K[0-9.]+/[0-9]+')
localNetworkScan=$(sudo nmap -O $localIPRange)
temp_file=$(mktemp)
echo $localNetworkScan | awk '/Nmap scan report for/{ip=$NF; gsub(/[()]/, "", ip)} /open/{print ip, $1}' > "$temp_file"
scan_with_vulners() {
    local ip=$1
    local ports=$2
    echo "Scanning $ip on ports $ports with vulners script"
    nmap -sV -Pn -p "$ports" --script=vulners "$ip"
    nmap -Pn -p80,443,8000,8080 -sV --script=http-enum --script=http-csrf --script http-passwd --script http-sql-injection --script --script http-cookie-flags "$ip"
}
while read -r ip port_info; do
    port=${port_info%/*}
    scan_with_vulners "$ip" "$port"
done < "$temp_file"
rm "$temp_file"

# other interesting



