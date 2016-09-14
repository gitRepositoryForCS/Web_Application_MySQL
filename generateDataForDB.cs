/*
This is the c# code we used to generate insert command in the records.sql 
(in order to create data for large table)
*/
using System;
using System.Collections.Generic;
using System.IO;
using System.Linq;
using System.Text;
using System.Threading;
using System.Threading.Tasks;


namespace ConsoleApplication2
{
    class Program
    {
       static string[] a = new string[26];

        static void Main(string[] args)
        {
            int j = 0;
            for (char i = 'a'; i <'z'; i++ )
            {

                a[j] = Convert.ToString(i);
                j++;
            }     
            Random rand = new Random();
            rand.Next(0, 25);
            string uid = "a";
            string password = "";
            string fullname = "";
            string email = "";
            int f=0;
            int l = 0;
            string insert = "insert into users(uid,password,fullname,email) values";
            using (StreamWriter w = new StreamWriter("..\\..\\..\\users.sql"))
            {
                w.WriteLine(insert);
                for (int i = 0; i < 150000; i++)
                {
                    uid += i.ToString();
                    password = uid + uid;
                    f = rand.Next(0, 25);
                    l = rand.Next(0, 25);
                    fullname = a[f] + a[l];
                    email = fullname + "@gamil.com";
                    insert = "(" + "'" + uid + "'," + "'" + password + "'," + "'" + fullname + "',"
                    + "'" + email + "'), ";
                    if (i == 149999) {
                        insert = insert.Substring(0, insert.Length - 2);
                        insert += ";";
                    }
                    w.WriteLine(insert); 
                    uid = "a";
                    password = "";

                } 
                w.Close();
            }
        }
    }
}
