#include <bits/stdc++.h>
using namespace std;
const int maxn = 500 + 10;
int n;
double eps = 0.0000000001;
struct node{
    string name;
    double speed1, speed2;
    bool operator <(const node &res) const{
        return speed2 < res.speed2;
    }
}Node[maxn];
vector<node> g;
int main()
{
    scanf("%d", &n);
    for(int i = 1; i <= n; i++)
    {
        cin>>Node[i].name>>Node[i].speed1>>Node[i].speed2;
    }
    double Min = 1000000000000.0;
    string s1, s2, s3, s4;
    for(int i = 1; i <= n; i++)
    {
        g.clear();
        double sum = Node[i].speed1;
        for(int j = 1; j <= n; j++)
        {
            if(j != i) g.push_back(Node[j]);
        }
        sort(g.begin(), g.end());
        for(int j = 0; j <= 2; j++)
        {
            sum += g[j].speed2;
        }
        if(sum - Min < eps)
        {
            Min = sum;
            s1 = Node[i].name;
            s2 = g[0].name;
            s3 = g[1].name;
            s4 = g[2].name;
        }

    }
    printf("%.2lf\n", Min);
    cout<<s1<<endl<<s2<<endl<<s3<<endl<<s4<<endl;
    return 0;
}