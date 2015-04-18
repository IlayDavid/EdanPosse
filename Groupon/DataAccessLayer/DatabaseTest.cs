using NUnit.Framework;
using System;
using System.Collections.Generic;
using System.Data.SqlClient;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;


namespace DataAccessLayer
{

    //SqlConnection  con="Server=.\SQLExpress;AttachDbFilename=|DataDirectory|\GDB.mdf;Database=GDB;Trusted_Connection=Yes;" ;
    [TestFixture]
    class DatabaseTest
    {
        [TearDown]
        public void cleanTables()
        {
            SqlConnection con = new SqlConnection(@"Data Source=(LocalDB)\v11.0;AttachDbFilename=|DataDirectory|\GDB.mdf;Integrated Security=True");
            try
            {
                con.Open();
                SqlCommand command = new SqlCommand();
                command.Connection = con;
                //By order of execution:
                string clearCoupons = "DELETE FROM Coupons"; // Must be first
                string clearOrders = "DELETE FROM Orders"; // Must be after Coupons and before Users
                string clearCatalog = "DELETE FROM Catalog"; // Must be after Coupons and before Businesses
                string clearBusinesses = "DELETE FROM Businesses"; // Must be after Catalog and before Users
                string clearAdmins = "DELETE FROM Admins"; // Must be before Users
                string clearUsers = "DELETE FROM Users"; //MUST BE LAST

                command.CommandText = clearCoupons;
                command.ExecuteNonQuery();

                command.CommandText = clearOrders;
                command.ExecuteNonQuery();

                command.CommandText = clearCatalog;
                command.ExecuteNonQuery();

                command.CommandText = clearBusinesses;
                command.ExecuteNonQuery();

                command.CommandText = clearAdmins;
                command.ExecuteNonQuery();

                command.CommandText = clearUsers;
                command.ExecuteNonQuery();
            }
            catch (Exception e)
            {
                MessageBox.Show(e.ToString());
            }
            if (con != null)
            {
                con.Close();
            }
        }

        [Test]
        public void Test01_Connection_1()
        {
            SqlConnection con = new SqlConnection(@"Data Source=(LocalDB)\v11.0;AttachDbFilename=|DataDirectory|\GDB.mdf;Integrated Security=True");
            string outcome = "";
            try
            {
                con.Open();
            }
            catch (Exception e)
            {
                outcome = e.ToString();
                MessageBox.Show(e.ToString());
            }
            if (con != null){
                con.Close();
                if (outcome.Equals(""))
                {
                    outcome = "success";
                }
            }
                
            else{
                outcome = "con = null";
            }
            Assert.AreEqual("success", outcome);
        }

        [Test]
        public void Test02_Add_User_1()
        {
            SqlConnection con = new SqlConnection(@"Data Source=(LocalDB)\v11.0;AttachDbFilename=|DataDirectory|\GDB.mdf;Integrated Security=True");
            string outcome = "";

            SqlDataReader reader = null;
            SqlCommand command = new SqlCommand();

            string insertString, selectString;
            insertString = "INSERT INTO Users (id, password, permission) VALUES ('1','1',1)";
            selectString = "SELECT * FROM Users WHERE id='1'";
            try
            {
                con.Open();
                command.Connection = con;

                outcome = "Before insertion";
                command.CommandText = insertString;
                command.ExecuteNonQuery();
                outcome = "After insertion";

                command.CommandText = selectString;
                reader = command.ExecuteReader();
                outcome = "After Selection";
                string result = "";
                while (reader.Read())
                {
                    result += reader["id"].ToString() + "," + reader["password"].ToString() + "," + reader["permission"].ToString();
                }
                outcome = result;
            }
            catch (Exception e)
            {
                MessageBox.Show(e.ToString());
            }
          
            if (reader != null)
                reader.Close();
            if (con != null){
                con.Close();
            }

            else{
                outcome = "con = null";
            }

            
            Assert.AreEqual("1,1,1", outcome);
        }

        [Test]
        public void Test03_Add_Same_User_Twice_1()
        {
            SqlConnection con = new SqlConnection(@"Data Source=(LocalDB)\v11.0;AttachDbFilename=|DataDirectory|\GDB.mdf;Integrated Security=True");
            string outcome = "";

            SqlDataReader reader = null;
            SqlCommand command = new SqlCommand();

            string insertString, selectString;
            insertString = "INSERT INTO Users (id, password, permission) VALUES ('1','1',1)";
            selectString = "SELECT * FROM Users WHERE id='1'";
            try
            {
                con.Open();
                command.Connection = con;

                outcome = "Before insertion";
                command.CommandText = insertString;
                command.ExecuteNonQuery();
                outcome = "After insertion 1";
                command.ExecuteNonQuery();
                outcome = "After insertion 2";

                command.CommandText = selectString;
                reader = command.ExecuteReader();
                outcome = "After Selection";
                string result = "";
                while (reader.Read())
                {
                    result += reader["id"].ToString() + "," + reader["password"].ToString() + "," + reader["permission"].ToString();
                }
                outcome = result;
            }
            catch (Exception e)
            {
                if (e.ToString().Contains("duplicate"))
                {
                    outcome = "Duplicate Primary Keys Error";
                }
            }

            if (reader != null)
                reader.Close();
            if (con != null)
            {
                con.Close();
            }

            else
            {
                outcome = "con = null";
            }


            Assert.AreEqual("Duplicate Primary Keys Error", outcome);
        }

        [Test]
        public void Test04_Add_Business_1()
        {
            SqlConnection con = new SqlConnection(@"Data Source=(LocalDB)\v11.0;AttachDbFilename=|DataDirectory|\GDB.mdf;Integrated Security=True");
            string outcome = "";

            SqlDataReader reader = null;
            SqlCommand command = new SqlCommand();

            string insertString, selectString;
            insertString = "INSERT INTO Businesses (businessName) VALUES ('1')";
            selectString = "SELECT * FROM Businesses WHERE businessName='1'";
            try
            {
                con.Open();
                command.Connection = con;

                outcome = "Before insertion";
                command.CommandText = insertString;
                command.ExecuteNonQuery();
                outcome = "After insertion 1";

                command.CommandText = selectString;
                reader = command.ExecuteReader();
                outcome = "After Selection";
                string result = "";
                while (reader.Read())
                {
                    result += reader["businessName"].ToString();
                }
                outcome = result;
            }
            catch (Exception e)
            {
                if (e.ToString().Contains("duplicate"))
                {
                    outcome = "Duplicate Primary Keys Error";
                }
            }

            if (reader != null)
                reader.Close();
            if (con != null)
            {
                con.Close();
            }

            else
            {
                outcome = "con = null";
            }


            Assert.AreEqual("1", outcome);
        }

        [Test]
        public void Test05_Add_Business_NonExistent_ownerId_1()
        {
            SqlConnection con = new SqlConnection(@"Data Source=(LocalDB)\v11.0;AttachDbFilename=|DataDirectory|\GDB.mdf;Integrated Security=True");
            string outcome = "";

            SqlDataReader reader = null;
            SqlCommand command = new SqlCommand();

            string insertString, selectString;
            insertString = "INSERT INTO Businesses (businessName, ownerId) VALUES ('1','2')";
            selectString = "SELECT * FROM Businesses WHERE businessName='1'";
            try
            {
                con.Open();
                command.Connection = con;

                outcome = "Before insertion";
                command.CommandText = insertString;
                command.ExecuteNonQuery();
                outcome = "After insertion 1";

                command.CommandText = selectString;
                reader = command.ExecuteReader();
                outcome = "After Selection";
                string result = "";
                while (reader.Read())
                {
                    result += reader["businessName"].ToString();
                }
                outcome = result;
            }
            catch (Exception e)
            {
                if (e.ToString().Contains("conflict") && e.ToString().Contains("FOREIGN") && e.ToString().Contains("constraint"))
                {
                    outcome = "Can't reference nonexistent owner";
                }
            }

            if (reader != null)
                reader.Close();
            if (con != null)
            {
                con.Close();
            }

            else
            {
                outcome = "con = null";
            }


            Assert.AreEqual("Can't reference nonexistent owner", outcome);
        }
        
        [Test]
        public void Test06_Add_Businesses_Existent_ownerId_1()
        {
            SqlConnection con = new SqlConnection(@"Data Source=(LocalDB)\v11.0;AttachDbFilename=|DataDirectory|\GDB.mdf;Integrated Security=True");
            string outcome = "";

            SqlDataReader reader = null;
            SqlCommand command = new SqlCommand();

            string insertString, insertBusinessString, selectString;
            insertString = "INSERT INTO Users (id, password, permission) VALUES ('1','1',1)";
            insertBusinessString = "INSERT INTO Businesses (businessName, ownerId) VALUES ('1','1')";
            selectString = "SELECT * FROM Businesses WHERE businessName='1'";
            try
            {
                con.Open();
                command.Connection = con;

                outcome = "Before user insertion";
                command.CommandText = insertString;
                command.ExecuteNonQuery();
                outcome = "After user insertion";

                command.CommandText = insertBusinessString;
                command.ExecuteNonQuery();
                outcome = "After business insertion";

                command.CommandText = selectString;
                reader = command.ExecuteReader();
                outcome = "After Selection";
                string result = "";
                while (reader.Read())
                {
                    result += reader["businessName"].ToString();
                }
                outcome = result;
            }
            catch (Exception e)
            {
                MessageBox.Show(e.ToString());
            }

            if (reader != null)
                reader.Close();
            if (con != null)
            {
                con.Close();
            }

            else
            {
                outcome = "con = null";
            }


            Assert.AreEqual("1", outcome);
        }

        [Test]
        public void Test07_Add_Catalog_Null_publisherId_1()
        {
            SqlConnection con = new SqlConnection(@"Data Source=(LocalDB)\v11.0;AttachDbFilename=|DataDirectory|\GDB.mdf;Integrated Security=True");
            string outcome = "";

            SqlDataReader reader = null;
            SqlCommand command = new SqlCommand();

            string insertString, selectString;
            insertString = "INSERT INTO Catalog (catalogNumber, quantity, description) VALUES (1, 1, '1')";
            selectString = "SELECT * FROM Catalog WHERE catalogNumber='1'";
            try
            {
                con.Open();
                command.Connection = con;

                outcome = "Before insertion";
                command.CommandText = insertString;
                command.ExecuteNonQuery();
                outcome = "After insertion";

                command.CommandText = selectString;
                reader = command.ExecuteReader();
                outcome = "After Selection";
                string result = "";
                while (reader.Read())
                {
                    result += reader["catalogNumber"].ToString();
                }
                outcome = result;
            }
            catch (Exception e)
            {
                if (e.ToString().Contains("NULL"))
                {
                    outcome = "Can't insert NULL into publisherId column";
                }
            }

            if (reader != null)
                reader.Close();
            if (con != null)
            {
                con.Close();
            }

            else
            {
                outcome = "con = null";
            }


            Assert.AreEqual("Can't insert NULL into publisherId column", outcome);
        }

        [Test]
        public void Test08_Add_Catalog_1()
        {
            SqlConnection con = new SqlConnection(@"Data Source=(LocalDB)\v11.0;AttachDbFilename=|DataDirectory|\GDB.mdf;Integrated Security=True");
            string outcome = "";

            SqlDataReader reader = null;
            SqlCommand command = new SqlCommand();

            string insertUserString, insertCatalogString, selectString;
            insertUserString = "INSERT INTO Users (id, password, permission) VALUES ('1','1',1)";
            insertCatalogString = "INSERT INTO Catalog (catalogNumber, quantity, description, publisherId) VALUES (1, 1, '1', '1')";
            selectString = "SELECT * FROM Catalog WHERE catalogNumber='1'";
            try
            {
                con.Open();
                command.Connection = con;

                outcome = "Before insertion";
                command.CommandText = insertUserString;
                command.ExecuteNonQuery();
                outcome = "After user insertion";

                command.CommandText = insertCatalogString;
                command.ExecuteNonQuery();
                outcome = "After catalog insertion";

                command.CommandText = selectString;
                reader = command.ExecuteReader();
                outcome = "After Selection";
                string result = "";
                while (reader.Read())
                {
                    result += reader["catalogNumber"].ToString();
                }
                outcome = result;
            }
            catch (Exception e)
            {
                MessageBox.Show(e.ToString());
            }

            if (reader != null)
                reader.Close();
            if (con != null)
            {
                con.Close();
            }

            else
            {
                outcome = "con = null";
            }


            Assert.AreEqual("1", outcome);
        }

        [Test]
        public void Test09_Update_Catalog_Nonexistent_publisherId_1()
        {
            SqlConnection con = new SqlConnection(@"Data Source=(LocalDB)\v11.0;AttachDbFilename=|DataDirectory|\GDB.mdf;Integrated Security=True");
            string outcome = "";

            SqlDataReader reader = null;
            SqlCommand command = new SqlCommand();

            string insertUserString, insertCatalogString, selectString, updateCatalogString;
            insertUserString = "INSERT INTO Users (id, password, permission) VALUES ('1','1',1)";
            insertCatalogString = "INSERT INTO Catalog (catalogNumber, quantity, description, publisherId) VALUES (1, 1, '1', '1')";
            updateCatalogString = "UPDATE Catalog SET publisherId='2' WHERE publisherId='1'";
            selectString = "SELECT * FROM Catalog WHERE catalogNumber='1'";
            try
            {
                con.Open();
                command.Connection = con;

                outcome = "Before insertion";
                command.CommandText = insertUserString;
                command.ExecuteNonQuery();
                outcome = "After user insertion";

                command.CommandText = insertCatalogString;
                command.ExecuteNonQuery();
                outcome = "After catalog insertion";

                command.CommandText = updateCatalogString;
                command.ExecuteNonQuery();
                outcome = "After catalog update";

                command.CommandText = selectString;
                reader = command.ExecuteReader();
                outcome = "After Selection";
                string result = "";
                while (reader.Read())
                {
                    result += reader["catalogNumber"].ToString();
                }
                outcome = result;
            }
            catch (Exception e)
            {
                if (e.ToString().Contains("conflict") && e.ToString().Contains("FOREIGN") && e.ToString().Contains("constraint"))
                {
                    outcome = "Can't reference nonexistent publisher";
                }
            }

            if (reader != null)
                reader.Close();
            if (con != null)
            {
                con.Close();
            }

            else
            {
                outcome = "con = null";
            }


            Assert.AreEqual("Can't reference nonexistent publisher", outcome);
        }

        [Test]
        public void Test10_Update_Catalog_Existent_publisherId_1()
        {
            SqlConnection con = new SqlConnection(@"Data Source=(LocalDB)\v11.0;AttachDbFilename=|DataDirectory|\GDB.mdf;Integrated Security=True");
            string outcome = "";

            SqlDataReader reader = null;
            SqlCommand command = new SqlCommand();

            string insertUserString1, insertUserString2, insertCatalogString, selectString, updateCatalogString;
            insertUserString1 = "INSERT INTO Users (id, password, permission) VALUES ('1','1',1)";
            insertUserString2 = "INSERT INTO Users (id, password, permission) VALUES ('2','1',1)";
            insertCatalogString = "INSERT INTO Catalog (catalogNumber, quantity, description, publisherId) VALUES (1, 1, '1', '1')";
            updateCatalogString = "UPDATE Catalog SET publisherId='2' WHERE publisherId='1'";
            selectString = "SELECT * FROM Catalog WHERE catalogNumber='1'";
            try
            {
                con.Open();
                command.Connection = con;

                outcome = "Before insertion";
                command.CommandText = insertUserString1;
                command.ExecuteNonQuery();
                outcome = "After user1 insertion";

                command.CommandText = insertUserString2;
                command.ExecuteNonQuery();
                outcome = "After user2 insertion";

                command.CommandText = insertCatalogString;
                command.ExecuteNonQuery();
                outcome = "After catalog insertion";

                command.CommandText = updateCatalogString;
                command.ExecuteNonQuery();
                outcome = "After catalog update";

                command.CommandText = selectString;
                reader = command.ExecuteReader();
                outcome = "After Selection";
                string result = "";
                while (reader.Read())
                {
                    result += reader["publisherId"].ToString();
                }
                outcome = result;
            }
            catch (Exception e)
            {
                MessageBox.Show(e.ToString());
            }

            if (reader != null)
                reader.Close();
            if (con != null)
            {
                con.Close();
            }

            else
            {
                outcome = "con = null";
            }


            Assert.AreEqual("2", outcome);
        }
    }
}
