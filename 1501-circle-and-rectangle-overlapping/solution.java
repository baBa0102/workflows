class Solution {
    public boolean checkOverlap(int radius, int xCenter, int yCenter, int x1, int y1, int x2, int y2) {
        int closestX = clamp(xCenter, x1, x2);
        int closestY = clamp(yCenter, y1, y2);
        int distX = xCenter - closestX;
        int distY = yCenter - closestY;
        int distanceSq = (distX * distX) + (distY * distY);
        return distanceSq <= radius * radius;
    }
    private int clamp(int val, int min, int max) {
        return Math.max(min, Math.min(max, val));
}
}
