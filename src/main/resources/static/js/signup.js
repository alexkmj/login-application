const {
  Button,
  Typography,
  TextField,
  Link,
  Container,
  Box,
  Grid,
} = MaterialUI;

/**
 * Signup component for user registration.
 * 
 * @class
 * 
 * @author Alex Koh
 * 
 * @extends React.Component
 */
class Signup extends React.Component {
  /**
   * Constructor for Signup component.
   * 
   * @constructor
   * @param {Object} props - Props passed to Signup component
   */
  constructor(props) {
    super(props);

    /**
     * State object for Signup component
     * 
     * @type {Object}
     * 
     * @property {string} name - Name of user
     * @property {string} username - Username of user
     * @property {string} password - Password of user
     * @property {string} confirmPassword - Password confirmation of user
     */
    this.state = {
      name: "",
      username: "",
      password: "",
      confirmPassword: "",
    };
  }

  /**
   * Submit function for Signup component.
   * 
   * @function
   * @returns {void}
   */
  submit() {
    // check if passwords match
    if (this.state.password !== this.state.confirmPassword) {
      alert("Passwords do not match");
      return;
    }

    // create user and redirect to login page if successful
    fetch("/api/v1/users", {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
      },
      body: JSON.stringify({
        name: this.state.name,
        username: this.state.username,
        password: this.state.password,
      }),
    })
      .then((response) => response.json())
      .then((data) => {
        if (data.error) {
          alert(data.error);
        } else {
          window.location.href = "/login";
        }
      });
  }

  /**
   * Render function for Signup component.
   * 
   * @returns {JSX.Element} - Signup component UI
   */
  render() {
    return (
      <Container maxWidth="sm" sx={{ marginTop: 4 }}>
        <Typography component="h1" variant="h5">
          Sign Up
        </Typography>
        <Box sx={{ mt: 1 }}>
          <TextField
            margin="normal"
            required
            fullWidth
            id="name"
            label="Name"
            name="name"
            autoComplete="name"
            autoFocus
            value={this.state.name}
            onChange={(e) => this.setState({ name: e.target.value })}
          />
          <TextField
            margin="normal"
            required
            fullWidth
            id="username"
            label="Username"
            name="username"
            autoComplete="username"
            value={this.state.username}
            onChange={(e) => this.setState({ username: e.target.value })}
          />
          <TextField
            margin="normal"
            required
            fullWidth
            name="password"
            label="Password"
            type="password"
            id="password"
            autoComplete="current-password"
            value={this.state.password}
            onChange={(e) => this.setState({ password: e.target.value })}
          />
          <TextField
            margin="normal"
            required
            fullWidth
            name="confirmPassword"
            label="Confirm Password"
            type="password"
            id="confirmPassword"
            autoComplete="current-password"
            value={this.state.confirmPassword}
            onChange={(e) => this.setState({ confirmPassword: e.target.value })}
          />
          <Button
            type="submit"
            fullWidth
            variant="contained"
            sx={{ mt: 3, mb: 2 }}
            onClick={(e) => this.submit()}
          >
            Sign Up
          </Button>
          <Grid container>
            <Grid item>
              <Link href="/login" variant="body2">
                {"Already have an account? Sign In"}
              </Link>
            </Grid>
          </Grid>
        </Box>
      </Container>
    );
  }
}

ReactDOM.render(<Signup />, document.getElementById("root"));
