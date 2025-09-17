class Solution:
    def twoSum(self, nums, target):
        """
        Given an array of integers nums and an integer target,
        return indices of the two numbers such that they add up to target.
        You may assume that each input would have exactly one solution,
        and you may not use the same element twice.
        """
        seen = {}  # Dictionary to store previously seen numbers and their indices
        for i, num in enumerate(nums):
            complement = target - num
            if complement in seen:
                # Found the pair; return their indices
                return [seen[complement], i]
            seen[num] = i  # Store the current number's index
        return []  # No solution case, though problem guarantees one

# Example usage:
sol = Solution()

nums1 = [2, 7, 11, 15]
target1 = 9
print(sol.twoSum(nums1, target1))  # Output: [0, 1]

nums2 = [3, 2, 4]
target2 = 6
print(sol.twoSum(nums2, target2))  # Output: [1, 2]

nums3 = [3, 3]
target3 = 6
print(sol.twoSum(nums3, target3))  # Output: [0, 1]

