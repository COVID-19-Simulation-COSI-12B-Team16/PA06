import matplotlib.pyplot as plt

'''
data_path: original data
center_path: computed cluster centers, using the K-means algorithm
'''
def plot(data_path, center_path, plot_path):
    data_xs = []
    data_ys = []
    with open(data_path) as f:
        lines = f.readlines()
        for line in lines:
            x, y = [float(i) for i in line.split()]
            data_xs.append(x)
            data_ys.append(y)

    center_xs = []
    center_ys = []
    with open(center_path) as f:
        lines = f.readlines()
        for line in lines:
            x, y = [float(i) for i in line.split()]
            center_xs.append(x)
            center_ys.append(y)

    plt.scatter(data_xs, data_ys, s=10, c=[(0.1,0.1,0.1)], alpha=0.1)
    plt.scatter(center_xs, center_ys, s=10, c=[(1,0,0)], alpha=0.7)
    plt.xlabel('x', fontsize=10)
    plt.ylabel('y', fontsize=10)
    plt.xticks(fontsize=7)
    plt.yticks(fontsize=7)
    plt.savefig(plot_path)
    plt.clf()

if __name__ == "__main__":
    plot('data/s1.txt', 'result/s1-cb.txt', 'result/s1-plot.png')
    plot('data/s2.txt', 'result/s2-cb.txt', 'result/s2-plot.png')
    plot('data/s3.txt', 'result/s3-cb.txt', 'result/s3-plot.png')
    plot('data/s4.txt', 'result/s4-cb.txt', 'result/s4-plot.png')