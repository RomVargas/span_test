import argparse
import collections

def parse_game_result(result_str):
    """
    It takes a string as input, splits it into a list of strings, and then returns a tuple of strings
    and integers
    
    :param result_str: The string to parse
    :return: a tuple containing the names of the two teams and their scores.
    """
    # Parse the input string and return a tuple containing the
    # names of the two teams and their scores
    parts = result_str.split(",")
    # The first part is the name of the first team
    team1 = ''.join(str(e) for e in parts[0].strip().split(" ")[:-1])
    # The second part is the score of the first team
    score1 = parts[0].strip().split(" ")[-1]

# The third part is the name of the second team
    team2 = ''.join(str(e) for e in parts[1].strip().split(" ")[:-1])
# The fourth part is the score of the second team
    score2 = parts[1].strip().split(" ")[-1]
    # team1, score1, team2, score2 = result_str.strip().split(", ")
    return (team1, int(score1), team2, int(score2))

def calculate_points(score1, score2):
    """
    It returns the number of points each team gets based on the scores
    
    :param score1: The score of the first team
    :param score2: The score of the second team
    :return: The number of points each team gets based on the scores
    """
    # Calculate the number of points each team gets based on the scores
    if score1 == score2:
        # In case of a draw, each team gets 1 point
        return 1, 1
    elif score1 > score2:
        # In case of a win, the winning team gets 3 points and the losing team gets 0 points
        return 3, 0
    else:
        # In case of a loss, the losing team gets 3 points and the winning team gets 0 points
        return 0, 3

def calculate_score():
    parser = argparse.ArgumentParser()
    parser.add_argument("--input-file", help="File containing the game results")
    args = parser.parse_args()

    # Create a dictionary to keep track of the teams and their scores
    scores = collections.defaultdict(int)

    # Read the game results from the input file
    with open(args.input_file, "r") as f:
        for line in f:
            team1, score1, team2, score2 = parse_game_result(line)
            # Calculate the number of points each team gets based on the scores
            points1, points2 = calculate_points(score1, score2)
            # Update the scores of the teams
            scores[team1] += points1
            scores[team2] += points2

    # Sort the teams based on their scores and names
    sorted_teams = sorted(scores, key=lambda x: (-scores[x], x))
    # Print the ranking table
    for i, team in enumerate(sorted_teams):
        print(f"{i + 1}. {team}, {scores[team]} pts")

if __name__ == "__main__":
    calculate_score()
