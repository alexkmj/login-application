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
      users: [],
    };
  }

  componentDidMount() {
    fetch("/api/v1/all-users", {
      method: "GET",
    })
      .then((response) => response.json())
      .then((data) => {
        this.setState({ users: data });
      });
  }

  render() {
    return (
      <Container maxWidth="md" sx={{ marginTop: 4 }}>
        <Typography component="h1" variant="h5">
          Restricted Page!
        </Typography>
        <TableContainer component={Paper} elevation="3" sx={{ marginTop: 4 }}>
          <Table aria-label="user information">
            <TableHead>
              <TableRow>
                <TableCell sx={{ color: "#5F249F" }}>ID</TableCell>
                <TableCell sx={{ color: "#5F249F" }}>Username</TableCell>
                <TableCell sx={{ color: "#5F249F" }}>Name</TableCell>
                <TableCell sx={{ color: "#5F249F" }}>Is Manager</TableCell>
                <TableCell sx={{ color: "#5F249F" }}>Actions</TableCell>
              </TableRow>
            </TableHead>
            <TableBody>
              {this.state.users.map((user) => (
                <TableRow key={user.id}>
                  <TableCell component="th" scope="row">
                    {user.id}
                  </TableCell>
                  <TableCell>{user.username}</TableCell>
                  <TableCell>{user.name}</TableCell>
                  <TableCell>{user.manager ? "Yes" : "No"}</TableCell>
                  <TableCell>
                    {!user.manager && (
                      <Button
                        onClick={() => {
                          fetch("/api/v1/users", {
                            method: "PUT",
                            headers: {
                              "Content-Type": "application/json",
                            },
                            body: JSON.stringify({
                              username: user.username,
                              field: "isManager",
                            }),
                          })
                            .then((response) => response.json())
                            .then((data) => {
                              if (data.success) {
                                window.location.reload();
                              }
                            });
                        }}
                      >
                        Make Manager
                      </Button>
                    )}
                  </TableCell>
                </TableRow>
              ))}
            </TableBody>
          </Table>
        </TableContainer>
      </Container>
    );
  }
}

ReactDOM.render(<Login />, document.getElementById("root"));
