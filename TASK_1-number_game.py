import random

def play_game():
    print("Welcome to the Number Game!")
    print("You need to guess the number I'm thinking of.")
    
    # Set the range for the random number
    lower_bound = 1
    upper_bound = 100
    max_attempts = 10  # Limit the number of attempts for the user
    total_score = 0
    
    play_again = "yes"
    
    while play_again.lower() in ["yes", "y"]:
        # Generate a random number
        random_number = random.randint(lower_bound, upper_bound)
        print(f"\nI'm thinking of a number between {lower_bound} and {upper_bound}. Can you guess it?")
        
        attempts = 0
        
        while attempts < max_attempts:
            try:
                # Prompt user for their guess
                guess = int(input(f"Attempt {attempts + 1}/{max_attempts}: Enter your guess: "))
            except ValueError:
                print("Invalid input! Please enter a number.")
                continue
            
            attempts += 1
            
            # Compare guess with the random number
            if guess == random_number:
                print(f"Congratulations! You guessed the correct number in {attempts} attempts.")
                total_score += max_attempts - attempts + 1  # Higher score for fewer attempts
                break
            elif guess < random_number:
                print("Too low! Try again.")
            else:
                print("Too high! Try again.")
        
        if attempts == max_attempts and guess != random_number:
            print(f"Sorry, you've run out of attempts! The correct number was {random_number}.")
        
        # Ask if the user wants to play again
        play_again = input("\nDo you want to play another round? (yes/no): ")
    
    # Display the final score
    print(f"\nGame over! Your total score is: {total_score}")

if __name__ == "__main__":
    play_game()