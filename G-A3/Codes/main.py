"""def func(a):
        count = 0
        dp = [0]*len(a)
        for i in range(len(a)):
            flag = True
            for j in range(i+1,len(a)):
                if a[i]%a[j]==0 or dp[i]==1:
                    flag = False
                if a[j]%a[i]==0:
                    dp[j]=1
            if(dp[i]==1):
                flag = False
            if(flag):
                count+=1
        return count

l = [24,11,8,3,16]
print(func(l))"""

def func(a):
    count = 0
    for i in range(1,len(a)):
        if(a[i]!=a[i-1]):
            count+=1
    return count

l = [2,2,2]
print(func(l))
