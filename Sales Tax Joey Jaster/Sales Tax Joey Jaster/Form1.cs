using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;

namespace Sales_Tax_Joey_Jaster
{
    public partial class Form1 : Form
    {
        public Form1()
        {
            InitializeComponent();
        }

        private void button1_Click(object sender, EventArgs e)
        {
            double totalprice = double.Parse(textBox1.Text);

            textBox3.Text = string.Format("{0:N2}", (totalprice * .04)).ToString();

            textBox2.Text = string.Format("{0:N2}", (totalprice * .04)).ToString();

            textBox4.Text = string.Format("{0:N2}", (totalprice + (totalprice * .04 + totalprice * .04))).ToString();

            //N2 is Number with two Decimals 
        }

        private void button2_Click(object sender, EventArgs e)
        {
            textBox1.Text = "";
            textBox2.Text = "";
            textBox3.Text = "";
            textBox4.Text = "";

        }

        private void button3_Click(object sender, EventArgs e)
        {
            this.Close();
        }
    }
}
