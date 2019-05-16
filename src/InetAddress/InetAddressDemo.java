package InetAddress;

import java.net.*;
import java.util.Enumeration;
 
public class InetAddressDemo {
    public static void main(String[] args) {
        try {
            Enumeration<NetworkInterface> interfaceList = NetworkInterface.getNetworkInterfaces();
            if (interfaceList == null){
                System.out.println("--No interfaces found--");
            }else {
                while (interfaceList.hasMoreElements()){
                    NetworkInterface iface = interfaceList.nextElement();
                    System.out.println("Interface " + iface.getName() + ":");
                    Enumeration<InetAddress> addrList = iface.getInetAddresses();
                    if (!addrList.hasMoreElements()){
                        System.out.println("\t(No addresses for hits interface)");
                    }
                    while (addrList.hasMoreElements()){
                        InetAddress address = addrList.nextElement();
                        System.out.println("\tAddress " + ((address instanceof Inet4Address ? "(v4)" :(address instanceof Inet6Address ? "(v6)" : "(?)"))));
                        System.out.println(": " + address.getHostAddress());
                    }
                }
            }
        }catch (SocketException se){
            System.out.println("Error getting network interfaces : " + se.getMessage());
        }
        for (String host : args){
            try {
                System.out.println(host + ":");
                InetAddress[] addressList = InetAddress.getAllByName(host);
                for (InetAddress address : addressList){
                    System.out.println("\t" + address.getHostAddress() + "/" + address.getHostAddress());
                }
            }catch (UnknownHostException e){

            }
        }
    }
}
