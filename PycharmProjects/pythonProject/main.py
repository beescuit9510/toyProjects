class Solution(object):
    def balancedStringSplit(self, s):
        r = ""
        index = 0
        pop = 0
        for str in s:
            if str == 'R':
                r += 'R'
            if str == 'L':
                r += 'L'

            if len(r) > 2:

            if r == 'RL' or r == 'LR':



# print(Solution().balancedStringSplit("hello"))
