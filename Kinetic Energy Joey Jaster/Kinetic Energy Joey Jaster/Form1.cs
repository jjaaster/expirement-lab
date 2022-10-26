using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;

namespace Kinetic_Energy_Joey_Jaster
{
    public partial class Form1 : Form
    {
        public Form1()
        {
            InitializeComponent();
        }

        private void button1_Click(object sender, EventArgs e)
        {
            double mass = int.Parse(textBox1.Text);
            double velocity = int.Parse(textBox2.Text);
            double KE = KineticEnergy(mass, velocity);
            MessageBox.Show("Kinetic Energy =" + KE.ToString());

        }

        private double KineticEnergy(double Mass, double velocity)
        {
            double KE = 0.5 * Mass * (velocity * velocity);
            return KE;
        }
    }
}
