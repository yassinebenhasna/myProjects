import numpy as np
import cv2 as cv
import matplotlib.pyplot as plt
from scipy.ndimage import interpolation as inter
from PIL import Image as im


def binary_otsus(image, filter:int=1):
    """Limiter une image entre 0 et 255 en utilisant la binarisation d'Otsu(Open cv)"""
#La forme de l'image est accessible par img.shape. Il renvoie un tuple de nombre de lignes, de colonnes et de canaux (si l'image est en couleur)
    if len(image.shape) == 3:
        gray_img = cv.cvtColor(image, cv.COLOR_BGR2GRAY)
    else:
        gray_img = image

    #binarisation d'Otsu
    if filter != 0:
        blur = cv.GaussianBlur(gray_img, (3,3), 0)
        binary_img = cv.threshold(blur, 0, 255, cv.THRESH_BINARY+cv.THRESH_OTSU)[1]
    else:
        binary_img = cv.threshold(gray_img, 0, 255, cv.THRESH_BINARY+cv.THRESH_OTSU)[1]

    return binary_img


def find_score(arr, angle):
    data = inter.rotate(arr, angle, reshape=False, order=0)
    hist = np.sum(data, axis=1)
    score = np.sum((hist[1:] - hist[:-1]) ** 2)
    return hist, score

def deskew(binary_img):

    
    ht, wd = binary_img.shape
    bin_img = (binary_img // 255.0)

    delta = 0.1
    limit = 3
    angles = np.arange(-limit, limit+delta, delta)
    scores = []
    for angle in angles:
        hist, score = find_score(bin_img, angle)
        scores.append(score)

    best_score = max(scores)
    best_angle = angles[scores.index(best_score)]
    data = inter.rotate(bin_img, best_angle, reshape=False, order=0)
    img = im.fromarray((255 * data).astype("uint8"))
    pix = np.array(img)
    return pix

def vexpand(gray_img, color:int):
    """Développez l'image d'un espace vertical dans les deux sens"""
    color = 1 if color > 0 else 0
    (h, w) = gray_img.shape[:2]
    space = np.ones((10, w)) * 255 * color
    return np.block([[space], [gray_img], [space]])
def hexpand(gray_img, color:int):
    """Développez l'image d'un espace horizontalement dans les deux directions"""
    color = 1 if color > 0 else 0
    (h, w) = gray_img.shape[:2]
    space = np.ones((h, 10)) * 255 * color
    return np.block([space, gray_img, space])
def valid(row, col, vis, word):
    return (row < vis.shape[0] and col < vis.shape[1] and row >= 0 and col >=0 and vis[row][col] == 0 and word[row][col] > 0)
def dfs(row, col, vis, word):

    dX = [0,0,1,1,-1,-1,1,-1]
    dY = [1,-1,0,1,0,-1,-1,1]
    vis[row][col] += 1
    for i in range(8):
        if(valid(row+dX[i],col+dY[i],vis, word)):
            dfs(row+dX[i], col+dY[i], vis, word)
    return

def erase_points(word , baseline):
    vis = np.zeros(word.shape)
    print(vis.shape)
    for i in range(word.shape[1]):
        if(vis[baseline][i]==0):
            dfs(baseline,i,vis,word)
    
    for i in range(baseline):
        for j in range(word.shape[1]):
            if(vis[i][j]==0):
                word[i][j] = 0