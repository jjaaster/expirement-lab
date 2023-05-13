using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;

namespace Mass_and_Weight
{
    public partial class Form1 : Form
    {
        public Form1()
        {
            InitializeComponent();
        }

        private void button1_Click(object sender, EventArgs e)
        {
            int mass = int.Parse(textBox1.Text);
            double weight = mass * 9.8;
            textBox2.Text = weight.ToString();

            if (weight > 1000)
                // Weight Greater than 1000 N

                MessageBox.Show("Mass is too Heavy");

            else if (weight < 10)
                // Weight Less than 10 N
                MessageBox.Show("Mass is too Light");
        }
    }
}
