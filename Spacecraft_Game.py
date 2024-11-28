import turtle, random, math

# I did number 3 and 8 for problem E



class Game():
    '''
    Purpose: 
        sets up the components of the game and runs it
    Instance variables: 
        self.player = the spaceship
        self.obs = the obstacles
    Methods: 
        def __init__(self): puts all the obstacles and the spaceship into the game
        def gameloop(self): makes everything move and runs the game
    '''

    def __init__(self):
        #Bottom left corner of screen is (0, 0)
        #Top right corner is (500, 500)
        turtle.setworldcoordinates(0, 0, 500, 500)
        cv = turtle.getcanvas()
        cv.adjustScrolls()
        turtle.bgcolor('black')
        #Ensure turtle is running as fast as possible
        turtle.delay(0)
        self.player = SpaceCraft(random.uniform(100,400), random.uniform(250,450), random.uniform(-4,4), random.uniform(-2,0))
        # self.player.goto(250, 250)
        self.obs = Obstacles(random.uniform(0,500), random.uniform(0,500), random.uniform(-4,4), random.uniform(-2,0))
        self.obs1 = Obstacles(random.uniform(0,500), random.uniform(0,500), random.uniform(-4,4), random.uniform(-2,0))
        self.obs2 = Obstacles(random.uniform(0,500), random.uniform(0,500), random.uniform(-4,4), random.uniform(-2,0))
        self.obs3 = Obstacles(random.uniform(0,500), random.uniform(0,500), random.uniform(-4,4), random.uniform(-2,0))
        self.obs4 = Obstacles(random.uniform(0,500), random.uniform(0,500), random.uniform(-4,4), random.uniform(-2,0))
        self.obs5 = Obstacles(random.uniform(0,500), random.uniform(0,500), random.uniform(-4,4), random.uniform(-2,0))
        self.obs6 = Obstacles(random.uniform(0,500), random.uniform(0,500), random.uniform(-4,4), random.uniform(-2,0))
        self.obs7 = Obstacles(random.uniform(0,500), random.uniform(0,500), random.uniform(-4,4), random.uniform(-2,0))
        self.obs8 = Obstacles(random.uniform(0,500), random.uniform(0,500), random.uniform(-4,4), random.uniform(-2,0))
        self.obs9 = Obstacles(random.uniform(0,500), random.uniform(0,500), random.uniform(-4,4), random.uniform(-2,0))
        self.obs10 = Obstacles(random.uniform(0,500), random.uniform(0,500), random.uniform(-4,4), random.uniform(-2,0))
        self.obs11 = Obstacles(random.uniform(0,500), random.uniform(0,500), random.uniform(-4,4), random.uniform(-2,0))
        self.obs12 = Obstacles(random.uniform(0,500), random.uniform(0,500), random.uniform(-4,4), random.uniform(-2,0))
        self.obs13 = Obstacles(random.uniform(0,500), random.uniform(0,500), random.uniform(-4,4), random.uniform(-2,0))
        self.obs14 = Obstacles(random.uniform(0,500), random.uniform(0,500), random.uniform(-4,4), random.uniform(-2,0))
        self.obs15 = Obstacles(random.uniform(0,500), random.uniform(0,500), random.uniform(-4,4), random.uniform(-2,0))
        self.obs16 = Obstacles(random.uniform(0,500), random.uniform(0,500), random.uniform(-4,4), random.uniform(-2,0))
        self.obs17 = Obstacles(random.uniform(0,500), random.uniform(0,500), random.uniform(-4,4), random.uniform(-2,0))
        self.obs18 = Obstacles(random.uniform(0,500), random.uniform(0,500), random.uniform(-4,4), random.uniform(-2,0))
        self.obs19 = Obstacles(random.uniform(0,500), random.uniform(0,500), random.uniform(-4,4), random.uniform(-2,0))

        turtle.tracer(0, 0)

        self.gameloop()


        turtle.onkeypress(self.player.thrust, 'Up')
        turtle.onkeypress(self.player.left_turn, 'Left')
        turtle.onkeypress(self.player.right_turn, 'Right')


        #These two lines must always be at the BOTTOM of __init__
        turtle.listen()
        turtle.mainloop()



    def gameloop(self):
        li = [self.obs, self.obs1, self.obs2, self.obs3, self.obs4, self.obs5, self.obs6, self.obs7, self.obs8, self.obs9, self.obs10, self.obs11, self.obs12, self.obs13, self.obs14, self.obs15, self.obs16, self.obs17, self.obs18, self.obs19]
        SpaceCraft.move(self.player)
        Obstacles.move(self.obs)
        Obstacles.move(self.obs1)
        Obstacles.move(self.obs2)
        Obstacles.move(self.obs3)
        Obstacles.move(self.obs4)
        Obstacles.move(self.obs5)
        Obstacles.move(self.obs6)
        Obstacles.move(self.obs7)
        Obstacles.move(self.obs8)
        Obstacles.move(self.obs9)
        Obstacles.move(self.obs10)
        Obstacles.move(self.obs11)
        Obstacles.move(self.obs12)
        Obstacles.move(self.obs13)
        Obstacles.move(self.obs14)
        Obstacles.move(self.obs15)
        Obstacles.move(self.obs16)
        Obstacles.move(self.obs17)
        Obstacles.move(self.obs18)
        Obstacles.move(self.obs19)


        turtle.color('white')


        if self.player.x_p < 0:
            self.player.goto(500, self.player.y_p)
        elif self.player.x_p > 500:
            self.player.goto(0, self.player.y_p)
        elif self.player.y_p > 500:
            self.player.goto(self.player.x_p, 100)

    


        if self.player.y_p < 10:
            if -3 <= self.player.x_v <= 3 and -3 <= self.player.y_v <= 3:
                turtle.write('Successful landing!', font = ('Verdana', 14, 'normal')) 
                return 
            else:
                turtle.write('You crashed!', font = ('Verdana', 24, 'normal'))
                return
        elif self.player.y_p > 10:
            for obstacle in li:
                if obstacle.xp < 0 or obstacle.yp > 500 or obstacle.xp > 500 or obstacle.yp < 0:
                    obstacle.xv *= -1
                    obstacle.yv *= -1
                dis = ((self.player.x_p - obstacle.xp)**2 + (self.player.y_p - obstacle.yp)**2)**(1/2)
                if dis < 8:
                    turtle.write('You crashed!', font = ('Verdana', 24, 'normal'))
                    return 
        # else:
        turtle.ontimer(self.gameloop, 30)
        turtle.update()



class SpaceCraft(turtle.Turtle):
    '''
    Purpose: 
        creates the spaceship
    Instance variables: 
        self.x_p = x coordinate
        self.y_p = y coordinate
        self.x_v = x velocity 
        self.y_v = y velocity
    Methods: 
        def __init__(self, x_p, y_p, x_v, y_v): sets up all the spaceship information
        def move(self): makes the ship move
        def thrust(self): makes the ship go up in any direction it is facing
        def left_turn(self): makes the ship turn to the left 15 degrees
        def right_turn(self): makes the ship move to the right 15 degrees
    '''

    def __init__(self, x_p, y_p, x_v, y_v):
        turtle.Turtle.__init__(self)
        self.color('white')
        self.x_p = x_p
        self.y_p = y_p
        self.x_v = x_v
        self.y_v = y_v
        self.fuel = 40
        self.left(90)
        self.penup()
        self.speed(0)
        self.goto(x_p, y_p)


    def move(self):
        self.y_v -= 0.0486
        self.x_p = self.xcor() + self.x_v
        self.y_p = self.ycor() + self.y_v
        self.goto(self.x_p, self.y_p)


    def thrust(self):
        if self.fuel > 0:
            self.fuel -= 1
            # math.radians(self.heading())
            self.x_v += math.cos(math.radians(self.heading()))
            self.y_v += math.sin(math.radians(self.heading()))
            print(self.fuel)
        else:
            print('Out of fuel')
    

    def left_turn(self):
        if self.fuel > 0:
            self.fuel -= 1
            self.left(15)
            print(self.fuel)
        else:
            print('Out of fuel')


    def right_turn(self):
        if self.fuel > 0:
            self.fuel -= 1
            self.right(15)
            print(self.fuel)

        else:
            print('Out of fuel')




class Obstacles(turtle.Turtle):
    '''
    Purpose: 
        create the obstacles for the game
    Instance variables: 
        self.xp = x coordinate for obstacle
        self.yp = y coordinate for obstacle
        self.xv = x velocity for obstacle
        self.yv = y velocity for obstacle
    Methods: 
        def __init__(self, xp, yp, xv, yv): sets up the information for the obstacles
        def move(self): moves the obstacles
    '''

    def __init__(self, xp, yp, xv, yv):
        turtle.Turtle.__init__(self)
        self.shape('circle')
        self.color('grey')
        self.xp = xp
        self.yp = yp
        self.xv = xv
        self.yv = yv
        self.penup()
        self.speed(0)
        self.goto(xp, yp)


    def move(self):
        self.yv -= 0.0486
        self.xp = self.xcor() + self.xv
        self.yp = self.ycor() + self.yv
        self.goto(self.xp, self.yp)






if __name__ == '__main__':
    Game()


