#include<iostream>
#include<iomanip>
#include<cmath>

using namespace std;

double getPhysic(double mass, double volume)
{

	double result = (.5) * mass * pow(volume, 2);
	return result;
}

int main()
{
	int input;
	double mass, volume, time;
	const double gravity = 9.8;

	cout << "1. Calculate the Falling Distance\n";
	cout << "2. Calculate the Kinetic Energy\n";
		cin >> input;

		if (input == 1)
		{
			
			cout << "enter the mass: ";
			cin >> mass;

			cout << "enter the volume: ";
			cin >> volume;

			double kinetic = getPhysic(mass, volume);
			cout << kinetic << endl;

		}

		else if (input == 2)
		{
	
			cout << "enter the time the object has fallen: ";
			cin >> time;

			double FD = getPhysic(gravity, time);
			cout << FD << endl;
		}

	system("pause");
	return 0;

}
