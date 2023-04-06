const {
  Button,
  Typography,
  AppBar,
  Toolbar,
  Paper,
  TextField,
  Link,
  Container,
  Box,
  TableContainer,
  Table,
  TableHead,
  TableRow,
  TableCell,
  TableBody,
  Grid,
} = MaterialUI;

class Signup extends React.Component {
  constructor(props) {
    super(props);

    this.state = {
      name: "",
      username: "",
      password: "",
      confirmPassword: "",
    };
  }

  submit() {
    if (this.state.password !== this.state.confirmPassword) {
      alert("Passwords do not match");
      return;
    }

    fetch("/api/v1/register", {
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

  render() {
    return (
      <div>
        <AppBar
          position="static"
          sx={{ backgroundColor: "#FFFFFF", color: "#5F249F" }}
        >
          <Toolbar>
            <Typography variant="h6" component="div" sx={{ flexGrow: 1 }}>
              Login Application
            </Typography>
          </Toolbar>
        </AppBar>
        <Container maxWidth="md" sx={{ marginTop: 4 }}>
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
              onChange={(e) =>
                this.setState({ confirmPassword: e.target.value })
              }
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
      </div>
    );
  }
}

ReactDOM.render(<Signup />, document.getElementById("root"));
