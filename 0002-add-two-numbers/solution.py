class Solution:
    def linkedListToNumber(self, l):
        num = 0
        place = 1
        current = l
        while current:
            num += current.val * place
            place *= 10
            current = current.next
        return num

    def createLinkedListFromString(self, s):
        head = ListNode(int(s[0]))
        current = head
        for digit in s[1:]:
            current.next = ListNode(int(digit))
            current = current.next
        return head

    def reverseNumberString(self, num):
        return str(num)[::-1]

    def addTwoNumbers(self, l1, l2):
        num1 = self.linkedListToNumber(l1)
        num2 = self.linkedListToNumber(l2)
        total = num1 + num2
        reversed_str = self.reverseNumberString(total)
        return self.createLinkedListFromString(reversed_str)

