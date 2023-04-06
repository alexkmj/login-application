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
} = MaterialUI;

class Login extends React.Component {
  constructor(props) {
    super(props);

    this.state = {
      name: "",
      username: "test",
      isManager: false,
    };
  }

  componentDidMount() {
    fetch("/api/v1/user")
      .then((response) => response.json())
      .then((data) => {
        this.setState({ name: data.name });
        this.setState({ username: data.username });
        this.setState({ isManager: data.isManager });
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
            <Button color="inherit" href="/logout">Logout</Button>
          </Toolbar>
        </AppBar>
        <Container maxWidth="md" sx={{ marginTop: 4 }}>
          <Typography component="h1" variant="h5">
            Welcome {this.state.name}!
          </Typography>
          <TableContainer component={Paper} elevation="3" sx={{ marginTop: 4 }}>
            <Table aria-label="user information">
              <TableHead>
                <TableRow>
                  <TableCell sx={{ color: "#5F249F" }}>Key</TableCell>
                  <TableCell sx={{ color: "#5F249F" }}>Value</TableCell>
                </TableRow>
              </TableHead>
              <TableBody>
                <TableRow key="name">
                  <TableCell component="th" scope="row">
                    Name
                  </TableCell>
                  <TableCell>{this.state.name}</TableCell>
                </TableRow>
                <TableRow key="username">
                  <TableCell component="th" scope="row">
                    Username
                  </TableCell>
                  <TableCell>{this.state.username}</TableCell>
                </TableRow>
                <TableRow key="isManager">
                  <TableCell component="th" scope="row">
                    Is Manager
                  </TableCell>
                  <TableCell>{this.state.isManager ? "Yes" : "No"}</TableCell>
                </TableRow>
              </TableBody>
            </Table>
          </TableContainer>
          {this.state.isManager && (
            <Button variant="contained" sx={{ marginTop: 4 }} href="/restricted">
              Restricted Webpage
            </Button>
          )}
        </Container>
      </div>
    );
  }
}

ReactDOM.render(<Login />, document.getElementById("root"));
