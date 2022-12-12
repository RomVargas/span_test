import winner_league

def test_parse_game_result():
    # Test with a result string with team names and scores separated by commas
    result_str = "team1 10, team2 20"
    expected_output = ("team1", 10, "team2", 20)
    assert winner_league.parse_game_result(result_str) == expected_output


def test_calculate_points():
    # Test a draw
    assert winner_league.calculate_points(1, 1) == (1, 1)

    # Test a win
    assert winner_league.calculate_points(2, 1) == (3, 0)

    # Test a loss
    assert winner_league.calculate_points(1, 2) == (0, 3)


    