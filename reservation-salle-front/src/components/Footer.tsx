import { Typography } from "@mui/material";
import Link from "@mui/material/Link";
import * as React from "react";

export default () =>
    <Typography variant="body2" color="text.secondary">
        {'Copyright © '}
        <Link color="inherit" href="">
            Room - M
        </Link>{' '}
        {new Date().getFullYear()}
        {'.'}
    </Typography>
